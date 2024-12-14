package com.properties;

import com.lib.comp.Button;
import com.lib.layout.VerticalFlowLayout;
import com.main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class PropertiesPanel extends JPanel implements com.lib.standard.PropertiesPanel, ActionListener {
    Properties currentProperties;
    JFrame parent;
    PanelWithProperties pwp;

    // gui
    Button saveBtn, backBtn;
    com.lib.comp.CheckBox darkThemeInp;

    public PropertiesPanel (Properties currentProperties, JFrame parent, PanelWithProperties pwp) {
        this.currentProperties = currentProperties;
        this.parent = parent;
        this.pwp = pwp;

        initComp();
        setLSD();
        syncGuiWithProperties(this.currentProperties);
        listen();
        addComp();
    }

    @Override
    public void initComp() {
        saveBtn = new Button("save");
        backBtn = new Button("back");
        darkThemeInp = new com.lib.comp.CheckBox("dark theme");
    }

    @Override
    public void setLSD() {
        setLayout(new VerticalFlowLayout());
    }

    @Override
    public void listen() {
        saveBtn.addActionListener(this);
        backBtn.addActionListener(this);
    }

    @Override
    public void addComp() {
        add(darkThemeInp);
        add(backBtn);
        add(saveBtn);
    }

    @Override
    public void setCompTheme() {

    }

    @Override
    public void syncGuiWithProperties(Properties p) {
        darkThemeInp.setSelected(p.get("darkTheme").equals("true"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveBtn)) {

            currentProperties.clear();
            currentProperties.setProperty("darkTheme", darkThemeInp.isSelected()+"");

            parent.dispose();
            pwp.writeProperties();

            int ans = JOptionPane.showConfirmDialog(this, "restart app?");
            if (ans!=JOptionPane.YES_OPTION)
                return;
            else
                Main.restart();

        } else if (e.getSource().equals(backBtn)) {
            parent.dispose();
        }
    }

}
