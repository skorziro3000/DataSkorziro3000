package Aiflaxe.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import Aiflaxe.Main.*;

import static Aiflaxe.Utils.Utils.logMsg;

public class Updater {

    private String outputFile;
    private URL url;
    private InputStream inputStream;
    private FileOutputStream fileOutputStream;
    private File folderMinecraft;
    private URLConnection urlconnection;

    int downloadB = 0;
    int allB = 0;
    int b;

    public Updater(String outputFile) {
        this.outputFile = outputFile;
    }

    public void downloadClient() {
        try {

            url = new URL(Utils.getClientUrl());
            url.openConnection();
            inputStream = url.openStream();

            folderMinecraft = new File(Utils.getMinecraftFolder());
            if(!folderMinecraft.exists()) folderMinecraft.mkdirs();

            fileOutputStream = new FileOutputStream(outputFile);

            byte[] buf = new byte[1024];

            urlconnection = new URL(url.toString()).openConnection();
            urlconnection.setDefaultUseCaches(false);
            allB += urlconnection.getContentLength();

            logMsg("Скачивание клиента...");
            while ((b = inputStream.read(buf)) > 0) {
                fileOutputStream.write(buf, 0, b);
                buf = new byte[1024];
                downloadB += b;
                Main.downloadProgressbar.setValue(downloadB, allB);
            }

            fileOutputStream.close();
            inputStream.close();

            logMsg("Распаковка клиента...");
            UnZip unZip = new UnZip(outputFile);
            unZip.unZip();

            Main.downloadProgressbar.clientExists(true);
        } catch (Exception e){}
    }
}
