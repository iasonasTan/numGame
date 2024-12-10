package com.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Properties;

import javax.swing.*;

import com.lib.comp.Button;
import com.lib.comp.Input;
import com.lib.comp.Label;
import com.lib.comp.NumberInput;
import com.lib.layout.VerticalFlowLayout;
import com.lib.listeners.keyListener.Action;
import com.lib.listeners.keyListener.ActionExecutor;
import com.lib.listeners.keyListener.KeyHandler;
import com.settings.*;
import com.user.UserManager;

public class MainGamePanel extends JPanel implements GamePanel, ActionExecutor {

    private UserManager um;
    private Settings settings;
    private SettingsStorage settingsS;

    // gui comp
    private NumberInput[] inputs;
    private JPanel inputsPanel;
    private Label[] outputs;
    private JPanel outputsPanel;

    private Button playBtn, returnToSettingsBtn, resetBtn;
    private Label mainOutput;
    private JPanel buttonsPanel;

    public MainGamePanel () {

        initComp();
        setLSD();
        listen();
        addComp();

        initUserMan();
    }

    private void initUserMan () {
        um = new UserManager(inputs, outputs);
    }
    
    public void returnToSettings (ActionEvent ae) {
        int ans = JOptionPane.showConfirmDialog(this, "are you sure ?");
        if (ans!=JOptionPane.YES_OPTION)
            return;

    	Main.setContentPane(new GetSettingsPanel());
    }

    public void play (ActionEvent ae) {
        boolean success = um.play();
        if (!success)
        	return;
        
        mainOutput.setText(um.getNearest()+"");
        inputs[0].requestFocus();
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
        returnToSettingsBtn = new Button("back");
        buttonsPanel = new JPanel();
        resetBtn = new Button("reset all");
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
        
        buttonsPanel.setLayout(new VerticalFlowLayout());

        for (Input i : inputs) {
        	i.addKeyListener(new KeyHandler(this));
        }

    }

    @Override
    public void listen() {
        playBtn.addActionListener(this::play);
        returnToSettingsBtn.addActionListener(this::returnToSettings);
        resetBtn.addActionListener(this::reset);
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
        
        add(mainOutput);
        
        buttonsPanel.add(playBtn);
        buttonsPanel.add(returnToSettingsBtn);
        buttonsPanel.add(resetBtn);
        
        add(buttonsPanel);
    }

    public void reset (ActionEvent ae) {
        int ans = JOptionPane.showConfirmDialog(this, "are you sure ?");
        if (ans!=JOptionPane.YES_OPTION)
            return;

        for (Input i : inputs)
            i.setText("");
        for (Label l : outputs)
            l.setText("");
        mainOutput.setText("");

        initUserMan();
    }

	@Override
	public void exec(Action a) {
		switch (a) {
		case PLAY:
			play(null);
			break;
			
		case BACK:
			returnToSettings(null);
			break;
			
		case RESET:
            reset(null);
			break;
			
		}
		
	}

}
