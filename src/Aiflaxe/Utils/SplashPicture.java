package Aiflaxe.Utils;

import Aiflaxe.Components.AMonitorProgressbar;
import Aiflaxe.Components.ANewsBlock;
import Aiflaxe.Components.ASplash;
import Aiflaxe.Main.Main;
import Aiflaxe.Main.Settings;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;

import static Aiflaxe.Utils.Utils.logMsg;

public class SplashPicture extends JFrame implements Runnable {

    private Image splashImage = Toolkit.getDefaultToolkit().getImage(SplashPicture.class.getResource("/Aiflaxe/Images/splash.png"));
    private ImageIcon splashIcon = new ImageIcon(splashImage);

    public static void main(String[] args) {
        SplashPicture splashPicture = new SplashPicture();
        splashPicture.init();
    }

    public void run() {
        main(null);
    }

    public void init() {
        try {
            ASplash splash = new ASplash(splashImage);
            splash.setBounds(0, 0, splashIcon.getIconWidth(), splashIcon.getIconHeight());
            add(splash);

            setSize(splashIcon.getIconWidth(), splashIcon.getIconHeight());
            setLocationRelativeTo(null);
            setUndecorated(true);
            setVisible(true);
            AWTUtilities.setWindowOpaque(this, false);

            logMsg("Загрузка компонентов...");
            new Main();

            for(int i = 0; i < Settings.servers.length; i++) { //Строим мониторинг
                new MonitorThread(i);
            }
            logMsg("Мониторинг построен");

            for(int j = 0; j < 5; j++) {//Строим новости
                News news = new News(j);
            }
            logMsg("Новости построены");

            Thread.sleep(3000);
            setVisible(false);
            Main.frame.setVisible(true);
            logMsg("Готов к работе");
            logMsg("Проверка на существование клиента...");
            Utils.checkClient();
        } catch (InterruptedException ie) {ie.printStackTrace();}
    }

}
