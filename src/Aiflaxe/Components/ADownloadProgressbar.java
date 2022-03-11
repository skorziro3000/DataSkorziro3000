package Aiflaxe.Components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ADownloadProgressbar extends JProgressBar {

    private BufferedImage progressBarBackground;
    private BufferedImage progressBarOnline;
    private BufferedImage check;

    public int downloadB, allB;
    public boolean isReady;

    public ADownloadProgressbar() {
        setFocusable(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    public void setValue(int bytesDownload, int bytesTotal) {
        allB = bytesTotal;
        downloadB = bytesDownload;
        repaint();
    }

    public void clientExists(boolean exists) {
        isReady = exists;
        repaint();
    }

    protected void paintComponent(Graphics graphics) {
        try {
            Graphics2D g = (Graphics2D) graphics.create();
            Font font = new Font("Verdana",Font.TRUETYPE_FONT ,14);
            String done = "Ваш клиент обновлен до последней версии";

            double wProgressBarOnline = 434.0 / allB * downloadB;
            double allMB = new BigDecimal(allB * 0.000001).setScale(2, RoundingMode.UP).doubleValue();
            double downloadMB = new BigDecimal(downloadB * 0.000001).setScale(2, RoundingMode.UP).doubleValue();
            double centerXDone = (441.0 / 2) - g.getFontMetrics(font).stringWidth(done) / 2;
            double centerXProgress = (441.0 / 2) - g.getFontMetrics(font).stringWidth((float)downloadMB + "/" + (float)allMB + " MB") / 2;

            String progress = (float)downloadMB + "/" + (float)allMB + " MB";

            g.setFont(font);
            g.setColor(Color.white);

            progressBarBackground = progressBarOnline = check = ImageIO.read(ADownloadProgressbar.class.getResource("/Aiflaxe/Images/progressbar_download.png"));

            progressBarBackground = progressBarBackground.getSubimage(41, 29, 441,36);
            progressBarOnline = progressBarOnline.getSubimage(45, 0, (int)wProgressBarOnline + 1, 29);
            check = check.getSubimage(0, 29, 41, 36);

            g.drawImage(progressBarBackground, 0, 0, progressBarBackground.getWidth(), progressBarBackground.getHeight(), null);

            if(!isReady) {
                g.drawImage(progressBarOnline, 2, 3, progressBarOnline.getWidth(), progressBarOnline.getHeight(), null);
                g.drawString(progress, (int)centerXProgress, 25);
            } else {
                g.drawImage(check, 0, 0, check.getWidth(), check.getHeight(), null);
                g.drawString(done, (int)centerXDone, 25);
            }

            g.dispose();
            super.paintComponent(graphics);
        } catch(Exception e){e.printStackTrace();}
    }

}