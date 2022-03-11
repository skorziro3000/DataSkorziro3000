package Aiflaxe.Utils;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class UnZip {

    private Enumeration enumeration;
    private ZipFile zipFile;
    private String pathToClient;
    private ZipEntry zipEntry;
    private File createFolder;
    private File deleteZip;

    public UnZip(String pathToClient) {
        this.pathToClient = pathToClient;
    }

    public void unZip() {
        try {
            zipFile = new ZipFile(pathToClient);
            enumeration = zipFile.entries();
            deleteZip = new File(zipFile.getName());

            while (enumeration.hasMoreElements()) {
                zipEntry = (ZipEntry) enumeration.nextElement();
                createFolder = new File(Utils.getMinecraftFolder()+ File.separator + zipEntry);

                if (zipEntry.isDirectory()) {
                    createFolder.mkdirs();
                    continue;
                }

                write(zipFile.getInputStream(zipEntry),new BufferedOutputStream(new FileOutputStream(Utils.getMinecraftFolder() + File.separator + zipEntry.getName())));
            }
        }
        catch (IOException e) {}
        finally {
            try{
                zipFile.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            deleteZip.delete();
        }
    }

    public void write(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buf = new byte[1024];
        int b;

        while ((b = inputStream.read(buf)) >= 0) {
            outputStream.write(buf, 0, b);
        }

        inputStream.close();
        outputStream.close();
    }
}

