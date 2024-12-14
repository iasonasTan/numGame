package com.properties;

import java.io.*;
import java.util.Properties;

public class PropertiesStorage {
    PanelWithProperties pwp;
    private final String FILE_PATH = "src/data/properties.data";

    public PropertiesStorage (PanelWithProperties pwp) {
        this.pwp = pwp;
    }

    public static Properties getProperties () { /* sorry man, I'm really scared of changing this file */
        PanelWithProperties pwp = new PanelWithProperties() {
            public Properties p;
            @Override
            public void setProperties(Properties p) {
                this.p = p;
            }
            @Override
            public Properties getProperties() {
                return p;
            }
            @Override public void writeProperties() {}
        };
        PropertiesStorage ps = new PropertiesStorage(pwp);
        ps.loadProperties();
        return pwp.getProperties();
    }

    private void createNewFile () {
        System.out.println("Creating new file");
        File dir = new File("src/data");
        File f = new File(FILE_PATH);
        try {
            dir.mkdir();
            f.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadProperties () {
        try {
            Properties out = getDefaultProperties();
            FileInputStream fis = new FileInputStream(FILE_PATH);
            out.load(fis);
            pwp.setProperties(out);
            System.out.println("Successful loaded settings");
            return;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pwp.setProperties(getDefaultProperties());
    }

    public void writeProperties () {
        try {
            Properties out = pwp.getProperties();
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            out.store(fos, " updated data");
            System.out.println("Successful wrote settings");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getDefaultProperties () {
        Properties out = new Properties();
        out.setProperty("darkTheme","false");
        return out;
    }

}
