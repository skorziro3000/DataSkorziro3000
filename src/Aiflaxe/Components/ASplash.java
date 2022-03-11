package Aiflaxe.Components;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ASplash extends JPanel {

    Image backgroundImage ;

    public ASplash(Image splashImage) {
        this.backgroundImage = splashImage;
        setOpaque(false);
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(backgroundImage, 0, 0, null);
    }
}
