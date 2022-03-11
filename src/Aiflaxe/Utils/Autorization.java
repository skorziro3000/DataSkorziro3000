package Aiflaxe.Utils;

import Aiflaxe.Main.Main;
import Aiflaxe.Main.Settings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.Timer;

import static Aiflaxe.Utils.Utils.logMsg;

public class Autorization {

    private URL authAdress;
    private URLConnection urlConnection;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public String getAnswer(String authUrl) {
        try {

            authAdress = new URL (authUrl);
            urlConnection = authAdress.openConnection();

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            dataOutputStream = new DataOutputStream(urlConnection.getOutputStream ());

            String parameters =
                    "user=" + Main.loginField.getText() +
                    "&password=" + Main.passwordField.getText() +
                    "&version=" + Settings.version;

            dataOutputStream.writeBytes(parameters);

            dataInputStream = new DataInputStream (urlConnection.getInputStream ());
            return dataInputStream.readLine();
        } catch (IOException ex) {}
        finally {
            try{
                dataInputStream.close ();
                dataOutputStream.close();
            } catch(Exception e) {}
        }
        return null;
    }

    public String getString(String answer) {
        if(answer.equals("Bad login")){
            if(Main.loginField.getText().isEmpty() || Main.passwordField.getText().isEmpty()) {
                timer.start();
                return "Введите логин и пароль";
            } else {
                timer.start();
                return "Неверный логин и/или пароль";
            }
        } else if(answer.equals("Old version")) {
            timer.start();
            return "Версия вашего лаунчера устарела";
        } else {
            logMsg("Запуск Minecraft клиента...");
            MinecraftClient client = new MinecraftClient(Main.loginField.getText(), answer, Settings.forge, null, null); // Последние два параметра - IP и Port
            client.start();
            System.exit(0);
        }
        return null;
    }

    private Timer timer = new Timer(2500, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Main.errorMsg.setText(null);
            timer.stop();
        }
    });
}
