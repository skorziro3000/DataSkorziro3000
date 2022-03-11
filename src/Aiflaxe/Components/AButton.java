package Aiflaxe.Components;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.JButton;

public class AButton extends JButton {

    private BufferedImage button;
    private BufferedImage buttonMove;
    private BufferedImage buttonPress;
    private String text;

    public AButton(String text) {
        this.text = text;
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    protected void paintComponent(Graphics graphics) {
        try {
            ButtonModel buttonModel = getModel();
            Font font = new Font("Verdana", Font.TRUETYPE_FONT, 12);

            button = buttonMove = buttonPress = ImageIO.read(AButton.class.getResource("/Aiflaxe/Images/button.png"));

            button = button.getSubimage(0, 0, 87, 28);
            buttonMove = buttonMove.getSubimage(0, 27, 87, 28);
            buttonPress = buttonPress.getSubimage(0, 54, 87, 28);

            double centerX = (87.0 / 2) - graphics.getFontMetrics(font).stringWidth(text) / 2;

            graphics.setFont(font);
            graphics.setColor(Color.white);

            if(buttonModel.isRollover()) {
                if(buttonModel.isPressed()) {
                    graphics.drawImage(buttonPress, 0, 0, buttonPress.getWidth(), buttonPress.getHeight(), null);
                } else graphics.drawImage(buttonMove, 0, 0, buttonMove.getWidth(), buttonMove.getHeight(), null);
            } else graphics.drawImage(button, 0, 0, button.getWidth(), button.getHeight(), null);

            graphics.drawString(text, (int)centerX, 18);
            graphics.dispose();
        } catch(Exception e){}
    }
}