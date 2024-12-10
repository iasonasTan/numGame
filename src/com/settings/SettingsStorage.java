package com.settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SettingsStorage {

    private PanelWithSettings pwp;
    private final String SETTINGS_PATH = "/src/data/settings.dat";

    public SettingsStorage(PanelWithSettings pwp) {
        this.pwp = pwp;
    }

    public void loadSettings () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SETTINGS_PATH));
            Settings s = (Settings)ois.readObject();
            pwp.setSettings(s);
            ois.close();

        } catch (Exception e) {
             System.out.println(e);
        }
    }

    public void writeSettings () {
        try {
            Settings s = pwp.getSettings();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SETTINGS_PATH));
            oos.writeObject(s);
            oos.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
