package Aiflaxe.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Aiflaxe.Components.AMonitorProgressbar;
import Aiflaxe.Main.Main;
import Aiflaxe.Main.Settings;

import static Aiflaxe.Utils.Utils.logMsg;

public class Monitoring {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private StringBuilder stringBuilder;
    private String reply;
    private int serverType;
    private int c;
    private static AMonitorProgressbar[] aProgressBarMonitors = new AMonitorProgressbar[Settings.servers.length];
    private boolean isOnline = true;

    public Monitoring(int i) {
        buildMonitor(getPlayers(getServerStatus(Settings.servers[i][1], Integer.parseInt(Settings.servers[i][2]), i)),Settings.servers[i][0], i , 148 + i * 40, isOnline);
    }

    public String[] getServerStatus(String hostName, int port, int serverNumber) {
        try {

            socket = new Socket(Settings.servers[serverNumber][1], Integer.parseInt(Settings.servers[serverNumber][2]));
            socket.setSoTimeout(3000);


            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.write(254);

            if (dataInputStream.read() != 255) {
                throw new IOException("Error");
            }

            if(socket == null || dataInputStream == null || dataOutputStream == null) {
                return new String[] { null, null, null, null, null, null};
            }

            c = dataInputStream.readChar();
            stringBuilder = new StringBuilder();
            for(int j = 0; j < c; j++) {
                stringBuilder.append(dataInputStream.readChar());
            }
            reply = stringBuilder.toString();

            if(reply.substring(0,1).equalsIgnoreCase("§") && reply.substring(1,2).equalsIgnoreCase("1")) {
                serverType = 1;
                return reply.split("\u0000");
            } else {
                serverType = 2;
                return reply.split("§");
            }

        } catch (Exception e) {
            return new String[] { null, null, null, null, null, null};
        } finally {
            if(socket == null) {
                return new String[] { null, null, null, null, null, null};
            } else {
                try {
                    socket.close();
                    dataInputStream.close();
                    dataOutputStream.close();
                } catch (Exception e) {}
            }
        }

    }

    public int[] getPlayers(String[] args) {
        if(serverType == 1) {
            if(args[4] == null && args[5] == null) {
                isOnline = false;
            } else {
                return new int[]{Integer.parseInt(args[4]), Integer.parseInt(args[5])};
            }
        } else {
            if(args[1] == null && args[2] == null){
                isOnline = false;
            } else {
                return new int[]{Integer.parseInt(args[1]), Integer.parseInt(args[2])};
            }
        }
        return new int[]{100, 100};
    }

    public void buildMonitor(int[] players, String serverName, int serverNumber, int posY, boolean online) {
        aProgressBarMonitors[serverNumber] = new AMonitorProgressbar(players[0], players[1], serverName, online);
        aProgressBarMonitors[serverNumber].setBounds(500, posY, 275, 33);
        Main.panel.add(aProgressBarMonitors[serverNumber]);
    }
}
