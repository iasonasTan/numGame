package com.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SettingsDisk {

    private PanelWithProperties pwp;
    private final String SETTINGS_PATH = "/src/data/settings.dat";

    public SettingsDisk(PanelWithProperties pwp) {
        this.pwp = pwp;
    }

    public void loadSettings () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SETTINGS_PATH));
            Settings s = (Settings)ois.readObject();


        } catch (Exception e) {
             System.out.println(e);
        }
    }

}
