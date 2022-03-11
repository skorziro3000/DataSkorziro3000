package Aiflaxe.Utils;

import Aiflaxe.Main.Settings;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MinecraftClient {

    private String playerName;
    private String session;
    private boolean forge;
    private String serverIP;
    private String serverPort;
    private String minecraftFolderPath;
    private String memory;
    private String minecraftJar;
    private String separatorLib;
    private String javaHome;
    private String libs = "";
    private ProcessBuilder processBuilder;

    public MinecraftClient(String playerName, String session, boolean forge, String serverIP, String serverPort){
        this.playerName = playerName;
        this.session = session;
        this.forge = forge;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void start() {
        try {
            minecraftFolderPath = Utils.getMinecraftFolder();
            memory = "1024M";
            minecraftJar = Utils.getMinecraftFolder() + File.separator + "minecraft.jar";
            javaHome = System.getProperty("java.home");

            if (Utils.getOS() == "Windows") {
                separatorLib = ";";
                javaHome+=File.separator + "bin" + File.separator + "javaw.exe";
            } else {
                separatorLib = ":";
                javaHome+=File.separator + "bin" + File.separator + "java.exe";
            }

            for (String lib : Utils.getClientLibs()) {
                libs += lib + separatorLib;
            }

            List<String> parameters = new ArrayList<String>();
            parameters.add(javaHome);
            parameters.add("-Xmx" + memory);
            parameters.add("-Djava.library.path=libraries/natives");
            parameters.add("-Dfml.ignoreInvalidMinecraftCertificates=true");
            parameters.add("-Dfml.ignorePatchDiscrepancies=true");
            parameters.add("-cp");
            parameters.add(libs + minecraftJar);

            if (!forge) {
                parameters.add("net.minecraft.client.main.Main");
            } else if (forge) {
                parameters.add("net.minecraft.launchwrapper.Launch");
                parameters.add("--tweakClass");
                parameters.add("cpw.mods.fml.common.launcher.FMLTweaker");
            }

            parameters.add("--username");
            parameters.add(playerName);
            parameters.add("--session");
            parameters.add(session);
            parameters.add("--version");
            parameters.add("1.6.2");
            parameters.add("--gameDir");
            parameters.add(minecraftFolderPath);
            parameters.add("--assetsDir");
            parameters.add(minecraftFolderPath + File.separator + "assets");
            parameters.add("--fullscreen");
            parameters.add("true");

            if(serverIP != null && serverPort != null) {
                parameters.add("--server");
                parameters.add(serverIP);
                parameters.add("--port");
                parameters.add(serverPort);
            }
            processBuilder = new ProcessBuilder();
            processBuilder.directory(new File(minecraftFolderPath).getCanonicalFile());
            processBuilder.command(parameters);
            processBuilder.inheritIO();
            processBuilder.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
