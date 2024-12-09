package com.main;

import com.lib.comp.Button;
import com.lib.comp.NumberInput;
import com.lib.comp.Slider;
import com.lib.layout.VerticalFlowLayout;
import com.settings.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetSettingsPanel extends JPanel implements ActionListener, GamePanel, PropertyChangeListener {
    private Button startGameBtn;
    private NumberInput maxValueInp;
    private JCheckBox isRandomNumInp, isShowSecretNumInp;
    private Slider secretNumInp, usersLenInp;

    public GetSettingsPanel () {
        initComp();
        setLSD();
        listen();
        addComp();
    }

    @Override
    public void initComp () {
        startGameBtn = new Button("start game");
        maxValueInp = new NumberInput("Enter max value...", Values.Preferences.PREFERRED_MAXIMUM_VALUE+"");
        isRandomNumInp = new JCheckBox("random number ?", true);
        isShowSecretNumInp = new JCheckBox("show secret num ?", false);
        secretNumInp = new Slider("select secret num");
        usersLenInp = new Slider("select users");
    }

    @Override
    public void setLSD () {
        setName("Settings");
        this.setLayout(new VerticalFlowLayout());
        secretNumInp.setValues(0, Values.Preferences.PREFERRED_MAXIMUM_VALUE, 1);
        usersLenInp.setValues(2, Values.Preferences.MAXIMUM_USERS, 2);
    }

    @Override
    public void listen () {
        startGameBtn.addActionListener(this);
        isRandomNumInp.addActionListener(ae ->
                secretNumInp.setEnabled(!isRandomNumInp.isEnabled()));
        maxValueInp.addPropertyChangeListener(this);
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
        add(isRandomNumInp);
        add(isShowSecretNumInp);
        add(secretNumInp);
        add(usersLenInp);
        add(startGameBtn);
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
        if (isRandomNumInp.isSelected()) {
            secretNum = (int)(Math.random()*maxVal);
        } else {
            secretNum = secretNumInp.getValue();
        }
        Settings.secretNum = secretNum;

        Settings.usersLen = usersLenInp.getValue();

        if (isShowSecretNumInp.isSelected()) {
            JOptionPane.showMessageDialog(null, "secretNum: "+secretNum);
        }

        Main.setContentPane(new MainGamePanel());

    }

}
