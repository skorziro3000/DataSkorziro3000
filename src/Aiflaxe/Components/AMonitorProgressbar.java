package Aiflaxe.Components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AMonitorProgressbar extends JProgressBar {

    private BufferedImage progressBarBackground;
    private BufferedImage progressBarOnline;
    private BufferedImage progressBarOffline;

    public int onlinePlayers, maxPlayers;
    public String serverName;
    public boolean online;

    public AMonitorProgressbar(int onlinePlayers, int maxPlayers, String serverName, boolean online) {
        this.onlinePlayers = onlinePlayers;
        this.maxPlayers = maxPlayers;
        this.serverName = serverName;
        this.online = online;
        setFocusable(false);
        setBorderPainted(false);
        setOpaque(false);
    }


    protected void paintComponent(Graphics graphics) {
        try {

            Graphics2D g = (Graphics2D) graphics.create();
            Font font = new Font("Verdana",Font.TRUETYPE_FONT ,13);

            String serverDataOnline = serverName + " " + onlinePlayers + "/" + maxPlayers;
            double centerXOnline = (275.0 / 2) - g.getFontMetrics(font).stringWidth(serverDataOnline) / 2;

            String serverDataOffline = "Сервер оффлайн";
            double centerXOffline = (275.0 / 2) - g.getFontMetrics(font).stringWidth(serverDataOffline) / 2;

            double wProgressBarOnline = 269.0 / (maxPlayers + 1) * (onlinePlayers + 1) ;

            progressBarBackground = progressBarOnline = progressBarOffline = ImageIO.read(AMonitorProgressbar.class.getResource("/Aiflaxe/Images/progressbar_monitor.png"));

            progressBarOnline = progressBarOnline.getSubimage(3, 34, (int)wProgressBarOnline, 25);
            progressBarBackground = progressBarBackground.getSubimage(0, 0, 275, 33);
            progressBarOffline = progressBarOffline.getSubimage(3, 60, 269, 25);

            g.drawImage(progressBarBackground, 0, 0, progressBarBackground.getWidth(), progressBarBackground.getHeight(), null);

            g.setFont(font);
            g.setColor(Color.white);

            if(!online) {
                g.drawImage(progressBarOffline, 4, 4, progressBarOffline.getWidth(), progressBarOffline.getHeight(), null);
                g.drawString(serverDataOffline, (int) centerXOffline, 20);
            } else {
                g.drawImage(progressBarOnline, 4, 4, progressBarOnline.getWidth(), progressBarOnline.getHeight(), null);
                g.drawString(serverDataOnline, (int) centerXOnline, 20);
            }

            g.dispose();
            super.paintComponent(graphics);
        } catch(Exception e){e.printStackTrace();}
    }

}
