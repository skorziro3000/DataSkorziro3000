package Aiflaxe.Components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Aiflaxe.Utils.Utils.logMsg;

public class ANewsBlock extends JComponent {

    private BufferedImage background;
    String[] handler;

    public ANewsBlock(String handler[]) {
        this.handler = handler;
    }

    protected void paintComponent(Graphics graphics) {
        try{
            Graphics2D g = (Graphics2D) graphics.create();
            Font font = new Font("Verdana", Font.CENTER_BASELINE, 12);
            Color color = new Color(34, 96, 154);
            String date = "[" + handler[0] + "]";
            String news = handler[1];

            int posXNews = g.getFontMetrics(font).stringWidth(date) + 15; // Координата по X оступа заголовка новости от начала блока новости
            background = ImageIO.read(ANewsBlock.class.getResource("/Aiflaxe/Images/news_block.png"));

            g.drawImage(background, 0, 0, null);
            g.setFont(font);
            g.setColor(color);
            g.drawString(date, 10, 26);
            g.setColor(Color.white);
            g.drawString(news, posXNews, 26);

            g.dispose();
            super.paintComponent(graphics);
        } catch (Exception e) {e.printStackTrace();}
    }


}
