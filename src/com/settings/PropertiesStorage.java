package com.settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesStorage {
    private final String FILE_PATH = "src/com/data/settings.properties";
    PanelWithProperties mp;

    public PropertiesStorage(PanelWithProperties mp) {
        this.mp = mp;
    }

    public void writeSettings () {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            Properties p = mp.getProperties();
            p.store(fos, "settings");
            fos.close();
        } catch (Exception e) {
            System.out.println("error while writing settings");
        }
    }

    public void loadProperties () {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            Properties p = null;
            p.load(fis);
            mp.setProperties(p);
            fis.close();
        } catch (Exception e) {
            System.out.println("Error while loading settings");
        }
    }

}
