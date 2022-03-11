package Aiflaxe.Components;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class ALoginField extends JTextField {

    private BufferedImage backgroundTextField;

    public ALoginField() {
        setOpaque(false);
        setBorder(new Border() {

            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {}
            public Insets getBorderInsets(Component c) {
                return new Insets(2, 10, 2, 5);
            }
            public boolean isBorderOpaque() {
                return true;
            }
        });
        setForeground(Color.white);
        setCaretColor(Color.white);
        setFont(new Font("Verdana", Font.TRUETYPE_FONT, 14));
    }
    protected void paintComponent(Graphics graphics) {
        try {
            Graphics2D g = (Graphics2D) graphics.create();

            backgroundTextField = ImageIO.read(ALoginField.class.getResource("/Aiflaxe/Images/textfield.png"));

            g.drawImage(backgroundTextField, 0, 0, backgroundTextField.getWidth(), backgroundTextField.getHeight(), null);
            g.dispose();
            super.paintComponent(graphics);
        } catch(Exception e){}
    }
}