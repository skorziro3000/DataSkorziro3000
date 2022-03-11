package Aiflaxe.Components;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.JButton;

public class AButtonSettings extends JButton {

    private BufferedImage button;
    private BufferedImage buttonMove;
    private BufferedImage buttonPress;

    public AButtonSettings() {
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    protected void paintComponent(Graphics graphics) {
        try {
            ButtonModel buttonModel = getModel();

            button = buttonMove = buttonPress = ImageIO.read(AButtonSettings.class.getResource("/Aiflaxe/Images/buttons_window.png"));

            button = button.getSubimage(0, 0, 27, 28);
            buttonMove = buttonMove.getSubimage(0, 30, 27, 28);
            buttonPress = buttonPress.getSubimage(0, 60, 27, 28);

            if(buttonModel.isRollover()) {
                if(buttonModel.isPressed()) {
                    graphics.drawImage(buttonPress, 0, 0, buttonPress.getWidth(), buttonPress.getHeight(), null);
                } else graphics.drawImage(buttonMove, 0, 0, buttonMove.getWidth(), buttonMove.getHeight(), null);
            } else graphics.drawImage(button, 0, 0, button.getWidth(), button.getHeight(), null);

            graphics.dispose();
        } catch(Exception e){}
    }
}