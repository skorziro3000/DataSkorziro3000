package Aiflaxe.Utils;

import Aiflaxe.Components.AMonitorProgressbar;
import Aiflaxe.Components.ANewsBlock;
import Aiflaxe.Main.Main;
import Aiflaxe.Main.Settings;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Utils {

    public static int numberLog = 0;

    public static void logMsg(String msg) {
        String prefix = "[Aiflaxe - " + numberLog + "]";
        System.out.println(prefix + msg);
        numberLog++;
    }

    public static String getOS() {
        String osName = System.getProperty("os.name").toLowerCase();

        if(osName.contains("win")) return "Windows";
        if(osName.contains("mac")) return "MacOS";
        if(osName.contains("linux")) return "Linux";

        return null;
    }

    public static String getMinecraftFolder() {
        String home = System.getProperty("user.home");
        String appData = System.getenv("AppData");
        String slash = File.separator;

        if (getOS().equals("Windows")) return appData + slash + Settings.folderMinecraft;
        if (getOS().equals("MacOS")) return home + slash + "Library/Application Support/" + Settings.folderMinecraft;
        if (getOS().equals("Linux")) return home + slash + Settings.folderMinecraft;

        return null;
    }

    public static String getClientUrl() {
        return Settings.urlSite + "clients" + "/" + "client.zip";
    }

    public static void checkClient() {
        File minecraftClient = new File(getMinecraftFolder());

        if (minecraftClient.exists()) {
            logMsg("Клиент существует, в обновлении не нуждается");
            Main.downloadProgressbar.clientExists(true);
        } else {
            logMsg("Клиент не найден. Идет скачивание...");
            Updater updater = new Updater(Utils.getMinecraftFolder() + File.separator + "client.zip");
            updater.downloadClient();
        }

    }

    public static void openURL(String url) {
        Desktop desktop = Desktop.getDesktop();
        URI uri;
        try {
            uri = new URI(url);
            desktop.browse(uri);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public static ArrayList<String> clientLibs = new ArrayList<String>(
            Arrays.asList(
                    //Библиотеки minecraft
                    "libraries/net/sf/jopt-simple/jopt-simple/4.5/jopt-simple-4.5.jar",
                    "libraries/org/ow2/asm/asm-all/4.1/asm-all-4.1.jar",
                    "libraries/org/lwjgl/lwjgl/lwjgl/2.9.0/lwjgl-2.9.0.jar",
                    "libraries/org/lwjgl/lwjgl/lwjgl_util/2.9.0/lwjgl_util-2.9.0.jar",
                    "libraries/org/lwjgl/lwjgl/lwjgl/2.9.0/jinput-2.0.5.jar",
                    "libraries/net/java/jutils/jutils/1.0.0/jutils-1.0.0.jar",
                    "libraries/com/paulscode/codecjorbis/20101023/codecjorbis-20101023.jar",
                    "libraries/com/paulscode/codecwav/20101023/codecwav-20101023.jar",
                    "libraries/com/paulscode/libraryjavasound/20101123/libraryjavasound-20101123.jar",
                    "libraries/com/paulscode/librarylwjglopenal/20100824/librarylwjglopenal-20100824.jar",
                    "libraries/com/paulscode/soundsystem/20120107/soundsystem-20120107.jar",
                    "libraries/argo/argo/2.25_fixed/argo-2.25_fixed.jar",
                    "libraries/org/bouncycastle/bcprov-jdk15on/1.47/bcprov-jdk15on-1.47.jar",
                    "libraries/com/google/guava/guava/14.0/guava-14.0.jar",
                    "libraries/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.jar",
                    "libraries/commons-io/commons-io/2.4/commons-io-2.4.jar",
                    "libraries/com/google/code/gson/gson/2.2.2/gson-2.2.2.jar",
                    //Библиотеки forge
                    "libraries/org/scala-lang/scala-library/2.10.2/scala-library-2.10.2.jar",
                    "libraries/org/scala-lang/scala-compiler/2.10.2/scala-compiler-2.10.2.jar",
                    "libraries/org/ow2/asm/asm-all/4.1/asm-all-4.1.jar",
                    "libraries/lzma/lzma/0.0.1/lzma-0.0.1.jar",
                    "forge/lwrap.jar",
                    "forge/mcforge.jar"
            )

    );

    public static ArrayList<String> getClientLibs() {
        return clientLibs;
    }

}
