package Aiflaxe.Main;

import Aiflaxe.Components.*;
import Aiflaxe.Utils.*;
import com.sun.awt.AWTUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static Aiflaxe.Utils.Utils.logMsg;

public class Main {

    Font fontErrorLabel = new Font("Verdana", Font.TRUETYPE_FONT, 14);
    int xMouse = 0, yMouse = 0;

    public static JFrame frame;
    public static JPanel panel;
    public static AButtonExit buttonExit;
    public static AButtonMinimize buttonMinimize;
    public static AButtonSettings buttonSettings;
    public static ALoginField loginField;
    public static APasswordField passwordField;
    public static AButton loginButton;
    public static AButton personalButton;
    public static ADownloadProgressbar downloadProgressbar;
    public static ASocialVK socialVK;
    public static ASocialT socialT;
    public static JLabel errorMsg;

    public Main() {

        frame = new JFrame();
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AWTUtilities.setWindowOpaque(frame, false);

        panel = new JPanel();
        panel.setSize(800, 500);
        panel.setOpaque(false);
        panel.setLayout(null);

        buttonExit = new AButtonExit();
        buttonExit.setBounds(761, 13, 27, 28);

        buttonMinimize = new AButtonMinimize();
        buttonMinimize.setBounds(729, 13, 27, 28);

        buttonSettings = new AButtonSettings();
        buttonSettings.setBounds(698, 13, 27, 28);

        loginField = new ALoginField();
        loginField.setBounds(494, 423, 176, 28);

        passwordField = new APasswordField();
        passwordField.setBounds(494, 457, 176, 28);

        loginButton = new AButton("Играть");
        loginButton.setBounds(686, 423, 87, 28);

        personalButton = new AButton("Кабинет");
        personalButton.setBounds(686, 457, 87, 28);

        downloadProgressbar = new ADownloadProgressbar();
        downloadProgressbar.setBounds(24, 437, 441, 36);

        socialVK = new ASocialVK();
        socialVK.setBounds(51, 13, 28, 28);

        socialT = new ASocialT();
        socialT.setBounds(11, 13, 28, 28);

        errorMsg = new JLabel();
        errorMsg.setForeground(Color.red);
        errorMsg.setFont(fontErrorLabel);
        errorMsg.setBounds(494, 390, 300, 20);

        frame.add(panel);
        frame.add(new ALoginPanel());
        panel.add(buttonExit);
        panel.add(buttonMinimize);
        panel.add(buttonSettings);
        panel.add(loginField);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(personalButton);
        panel.add(downloadProgressbar);
        panel.add(socialVK);
        panel.add(socialT);
        panel.add(errorMsg);

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getX() + frame.getX() - xMouse, e.getY() + frame.getY() - yMouse);

            }
        });
        panel.addMouseListener(new MouseListener(){
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX(); yMouse = e.getY();
            }
            public void mouseClicked(MouseEvent event){}
            public void mouseEntered(MouseEvent arg0) {}
            public void mouseExited(MouseEvent arg0) {}
            public void mouseReleased(MouseEvent arg0) {}
        });

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        buttonMinimize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frame.setExtendedState(JFrame.ICONIFIED);
            }
        });

        socialT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Utils.openURL(Settings.Twitter);
            }
        });

        socialVK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Utils.openURL(Settings.VK);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Autorization autorization = new Autorization();
                String answer = autorization.getAnswer("http://webmcr.ameden.net/MineCraft/auth.php");
                errorMsg.setText(autorization.getString(answer));
            }
        });
    }

    public static void main(String[] args) {
        logMsg("Запуск...");
        Thread thread = new Thread(new SplashPicture());
        thread.start();

    }

}
