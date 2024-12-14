package com.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Properties;

import com.lib.comp.CheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.lib.comp.Button;
import com.lib.comp.NumberInput;
import com.lib.comp.Slider;
import com.lib.layout.VerticalFlowLayout;
import com.lib.standard.GamePanel;
import com.lib.standard.Panel;
import com.lib.standard.PropertiesPanel;
import com.properties.PanelWithProperties;
import com.properties.PropertiesStorage;
import com.settings.Settings;

public class GetSettingsPanel extends Panel implements ActionListener, GamePanel, PropertyChangeListener, PropertiesPanel, PanelWithProperties {

    // gui comp
    private Button startGameBtn;
    private NumberInput maxValueInp;
    private com.lib.comp.CheckBox randomNumInp, showSecretNumInp, showFunctionsInp;
    private Slider secretNumInp, usersLenInp;
    private Color darkColor;
    private Color lightColor;
    private Color foreground;

    public GetSettingsPanel () {
        Properties p = PropertiesStorage.getProperties();

        initComp();
        setLSD();
        listen();
        syncGuiWithProperties(p);
        addComp();
    }

    @Override
    public void initComp () {
        startGameBtn = new Button("start game");
        maxValueInp = new NumberInput("Enter max value...", Values.Preferences.PREFERRED_MAXIMUM_VALUE+"");
        randomNumInp = new CheckBox("random number ?", true);
        showSecretNumInp = new CheckBox("show secret num ?", false);
        secretNumInp = new Slider("select secret num");
        usersLenInp = new Slider("select users");
        showFunctionsInp = new com.lib.comp.CheckBox("show functions ?");
    }

    @Override
    public void setLSD () {
        setName("Settings");
        setSize(new Dimension(500, 500));
        this.setLayout(new VerticalFlowLayout(5,5));
        secretNumInp.setValues(0, Values.Preferences.PREFERRED_MAXIMUM_VALUE, 1);
        usersLenInp.setValues(2, Values.Preferences.MAXIMUM_USERS, 2);
        setPreferredSize(Values.Dimensions.GET_SETTINGS_SIZE);
    }

    @Override
    public void listen () {
        startGameBtn.addActionListener(this);

        randomNumInp.addActionListener(ae ->
                secretNumInp.setEnabled(!randomNumInp.isEnabled()));

        maxValueInp.addPropertyChangeListener(this);

        randomNumInp.addActionListener(ae -> {
            if (randomNumInp.isSelected()) {
                showSecretNumInp.setEnabled(true);

                secretNumInp.setEnabled(false);
                secretNumInp.setValue(0);
            } else {
                showSecretNumInp.setSelected(false);
                showSecretNumInp.setEnabled(false);

                secretNumInp.setEnabled(true);
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String maxValStr = maxValueInp.getText();
        if (maxValStr.isBlank())
            return;
        int maxValInt = Integer.parseInt(maxValStr);
        secretNumInp.setMaximum(maxValInt);
    }

    @Override
    public void addComp () {
        add(maxValueInp);
        add(randomNumInp);
        add(showSecretNumInp);
        add(secretNumInp);
        add(showFunctionsInp);

        add(usersLenInp);
        add(startGameBtn);
    }

    @Override
    public void setCompTheme() {
        setBackground(lightColor);
        setForeground(foreground);

        startGameBtn.setBackground(darkColor);
        maxValueInp.setBackground(darkColor);
        randomNumInp.setBackground(darkColor);
        showSecretNumInp.setBackground(darkColor);
        showFunctionsInp.setBackground(darkColor);
        secretNumInp.setBackground(darkColor);
        usersLenInp.setBackground(darkColor);

        startGameBtn.setForeground(foreground);
        maxValueInp.setForeground(foreground);
        randomNumInp.setForeground(foreground);
        showSecretNumInp.setForeground(foreground);
        showFunctionsInp.setForeground(foreground);
        secretNumInp.setForeground(foreground);
        usersLenInp.setForeground(foreground);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String maxValStr = maxValueInp.getText();
        if (maxValStr.isBlank()) {
            return;
        }
        int maxVal = Integer.parseInt(maxValStr);
        Settings.maxVal = maxVal;

        int secretNum;
        if (randomNumInp.isSelected()) {
            secretNum = (int)(Math.random()*maxVal);
        } else {
            secretNum = secretNumInp.getValue();
        }
        Settings.secretNum = secretNum;

        Settings.usersLen = usersLenInp.getValue();

        if (showSecretNumInp.isSelected()) {
            JOptionPane.showMessageDialog(Main.f, "secretNum: "+secretNum);
        }

        Settings.showFuncs = showFunctionsInp.isSelected();

        Main.setContentPane(new MainGamePanel());

    }

    @Override
    public void syncGuiWithProperties(Properties p) {
        if (p.get("darkTheme").equals("true")) {
            darkColor = Values.Colors.DARK_BACKGROUND1;
            lightColor = Values.Colors.LIGHT_BACKGROUND1;
            foreground = Color.WHITE;
        } else {
            darkColor = Values.Colors.DARK_BACKGROUND2;
            lightColor = Values.Colors.LIGHT_BACKGROUND2;
            foreground = Color.BLACK;
        }
        setCompTheme();
    }

    @Override public void setProperties(Properties p) {}
    @Override public Properties getProperties() { return null; }
    @Override public void writeProperties() {}

}
