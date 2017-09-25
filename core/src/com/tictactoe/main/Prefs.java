package com.tictactoe.main;

import org.json.JSONObject;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

/**
 * Created by josephstewart on 8/4/17.
 */
public class Prefs {

    private JSONObject prefs;

    private File home;
    private File prefsFile;

    public Prefs() {
        this.prefs = new JSONObject();
        File file = FileSystemView.getFileSystemView().getHomeDirectory();
        home = new File(file,"CampaignBuddy");
        if (!home.exists()) {
            home.mkdirs();
            prefsFile= new File(home, "/settings.dat");
            try {
                if (!prefsFile.exists()) {
                    prefsFile.createNewFile();
                    PrintWriter writer = new PrintWriter(new FileWriter(prefsFile));
                    writer.println("{}");
                    writer.close();
                }

                BufferedReader reader = new BufferedReader(new FileReader(prefsFile));
                String line = reader.readLine();
                reader.close();

                System.out.println(line);

                this.prefs = new JSONObject(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        prefsFile = null;
    }

    public void save() {
        if (prefsFile != null) {
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(prefsFile));
                writer.println(prefs.toString());
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public JSONObject getPrefs() {
        return prefs;
    }

}
