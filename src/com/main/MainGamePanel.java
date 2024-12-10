package com.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.lib.comp.Button;
import com.lib.comp.Input;
import com.lib.comp.Label;
import com.lib.comp.NumberInput;
import com.lib.layout.VerticalFlowLayout;
import com.lib.listener.Action;
import com.lib.listener.ActionExecutor;
import com.lib.listener.KeyHandler;
import com.settings.Settings;
import com.user.UserManager;

public class MainGamePanel extends JPanel implements GamePanel, ActionExecutor {

    private final UserManager um;

    // gui comp
    private NumberInput[] inputs;
    private JPanel inputsPanel;
    private Label[] outputs;
    private JPanel outputsPanel;
    
    private KeyHandler mainKeyListener;

    private Button playBtn;
    private Label mainOutput;
    private Button returnToSettingsBtn;
    private JPanel buttonsPanel;

    public MainGamePanel () {

        initComp();
        setLSD();
        listen();
        addComp();

        um = new UserManager(inputs, outputs);
    }
    
    public void returnToSettings (ActionEvent ae) {
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
        
        mainKeyListener = new KeyHandler(this);
        
        for (Input i : inputs) {
        	i.addKeyListener(mainKeyListener);
        }
    }

    @Override
    public void listen() {
        playBtn.addActionListener(this::play);
        returnToSettingsBtn.addActionListener(this::returnToSettings);
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
        
        add(buttonsPanel);
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
			// TODO create reset method
			break;
			
		}
		
	}

}
