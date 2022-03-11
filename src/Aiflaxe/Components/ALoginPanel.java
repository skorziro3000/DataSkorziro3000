package Aiflaxe.Components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ALoginPanel extends JPanel {

    URL backgroundURL = ALoginPanel.class.getResource("/Aiflaxe/Images/background_login.png");
    Image backgroundImage = new ImageIcon(backgroundURL).getImage();

    URL logotypeURL = ALoginPanel.class.getResource("/Aiflaxe/Images/logotype.png");
    Image logotypeImage = new ImageIcon(logotypeURL).getImage();

    public void paint(Graphics graphics) {
        try {

            graphics.drawImage(backgroundImage, 0, 0, null);
            graphics.drawImage(logotypeImage, 313, 66, null);
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, 799, 499);
        } catch(Exception e){}
    }
}
