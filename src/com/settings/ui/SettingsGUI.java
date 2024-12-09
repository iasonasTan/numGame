package com.settings.ui;

import com.lib.comp.Button;
import com.lib.layout.VerticalFlowLayout;

import javax.swing.*;

public class SettingsGUI extends JPanel {

    // gui comp
    private Button saveBtn, backBtn;
    private JCheckBox darkThemeInp;

    public SettingsGUI () {
        initComp();
    }

    private void addComp () {
        add(darkThemeInp);
        add(saveBtn);
        add(backBtn);
    }

    private void  setLSD () {
        setName("settings");
        setLayout(new VerticalFlowLayout());
    }

    private void initComp () {
        saveBtn = new Button("save");
        backBtn = new Button("esc");
        darkThemeInp = new JCheckBox("dark theme");
    }

}
