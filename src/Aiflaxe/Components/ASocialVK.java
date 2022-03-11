package Aiflaxe.Components;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.JButton;

public class ASocialVK extends JButton {

    private BufferedImage button;
    private BufferedImage buttonMove;
    private BufferedImage buttonPress;

    public ASocialVK() {
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    protected void paintComponent(Graphics graphics) {
        try {
            ButtonModel buttonModel = getModel();

            button = buttonMove = buttonPress = ImageIO.read(AButtonMinimize.class.getResource("/Aiflaxe/Images/buttons_social.png"));

            button = button.getSubimage(40, 0, 28, 28);
            buttonMove = buttonMove.getSubimage(40, 31, 28, 28);
            buttonPress = buttonPress.getSubimage(40, 62, 28, 28);

            if(buttonModel.isRollover()) {
                if(buttonModel.isPressed()) {
                    graphics.drawImage(buttonPress, 0, 0, buttonPress.getWidth(), buttonPress.getHeight(), null);
                } else graphics.drawImage(buttonMove, 0, 0, buttonMove.getWidth(), buttonMove.getHeight(), null);
            } else graphics.drawImage(button, 0, 0, button.getWidth(), button.getHeight(), null);

            graphics.dispose();
        } catch(Exception e){}
    }
}