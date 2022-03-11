package Aiflaxe.Utils;

import Aiflaxe.Components.ANewsBlock;
import Aiflaxe.Main.Main;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static Aiflaxe.Utils.Utils.logMsg;

public class News {

    private URL url;
    private URLConnection urlConnection;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;
    private static ANewsBlock[] aNewsBlocks = new ANewsBlock[5];

    public News(int numberBlock) {
        buildNews(handler(request(numberBlock + 1)), numberBlock);
    }

    private String request(int number) {
        try{
            url = new URL("http://files.ameden.net/launcher_web/news.php" + "?id=" + number);
            urlConnection = url.openConnection();

            inputStreamReader = new InputStreamReader(urlConnection.getInputStream(), "windows-1251");
            bufferedReader = new BufferedReader(inputStreamReader);
            String c;
            while ((c = bufferedReader.readLine()) != null) {
                return c;
            }
        }
        catch (Exception e) {}
        finally {
            try { bufferedReader.close();} catch (Exception e) {}
            try { inputStreamReader.close();} catch (Exception e) {}
        }
        return null;
    }

    public String[] handler(String response) {
        return response.split("~");
    }

    public void buildNews(String[] handler, int j) {
        aNewsBlocks[j] = new ANewsBlock(handler);
        aNewsBlocks[j].setBounds(24, 130 + j * 46, 340, 42);
        Main.panel.add(aNewsBlocks[j]);
    }
}