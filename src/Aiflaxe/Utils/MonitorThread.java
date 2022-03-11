package Aiflaxe.Utils;

import Aiflaxe.Main.*;


public class MonitorThread implements Runnable {

    private int i;
    private Monitoring[] monitorings = new Monitoring[Settings.servers.length];
    private Thread thread;

    public MonitorThread(int i) {
        this.i = i;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        int i = this.i;
        monitorings[i] = new Monitoring(i);
        Main.frame.repaint();

    }
}
