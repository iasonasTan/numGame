package com.main;

import com.lib.comp.NumberInput;
import com.lib.comp.Label;
import com.lib.layout.VerticalFlowLayout;
import com.settings.Settings;
import com.user.UserManager;
import com.lib.comp.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainGamePanel extends JPanel implements GamePanel {

    private final UserManager um;

    // gui comp
    private NumberInput[] inputs;
    private JPanel inputsPanel;
    private Label[] outputs;
    private JPanel outputsPanel;

    private Button playBtn;
    private Label mainOutput;

    public MainGamePanel () {

        initComp();
        setLSD();
        listen();
        addComp();

        um = new UserManager(inputs, outputs);
    }

    public void play (ActionEvent ae) {
        boolean success = um.play();
        if (success)
            mainOutput.setText(um.getNearest()+"");
    }

    @Override
    public void initComp() {
        inputs = new NumberInput[Settings.usersLen];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = new NumberInput("user: "+(i+1));
        }
        inputsPanel = new JPanel();

        outputs = new Label[Settings.usersLen];
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = new Label("Hello!");
        }
        outputsPanel = new JPanel();

        playBtn = new Button("play");
        mainOutput = new Label();
    }

    @Override
    public void setLSD() {
        setName("MainGame");
        setLayout(new GridLayout());

        VerticalFlowLayout vfl = new VerticalFlowLayout();
        inputsPanel.setLayout(vfl);
        final int INPUTS_HEIGHT = Values.Dimensions.INPUT_SIZE.height*(inputs.length+vfl.gethGap());
        inputsPanel.setPreferredSize(new Dimension(Values.Dimensions.INPUT_SIZE.width, INPUTS_HEIGHT));
        inputsPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

        outputsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        outputsPanel.setPreferredSize(inputsPanel.getPreferredSize());
        outputsPanel.setLayout(new VerticalFlowLayout());
    }

    @Override
    public void listen() {
        playBtn.addActionListener(this::play);
    }

    @Override
    public void addComp() {
        for (NumberInput inp : inputs) {
            inputsPanel.add(inp);
        }
        add(inputsPanel);

        for (Label l : outputs) {
            outputsPanel.add(l);
        }
        add(outputsPanel);

        add(playBtn);
        add(mainOutput);
    }

}
