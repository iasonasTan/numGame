package com.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;

import com.lib.comp.*;
import com.lib.comp.Button;
import com.lib.comp.Frame;
import com.lib.comp.Label;
import com.lib.layout.VerticalFlowLayout;
import com.lib.listeners.keyListener.Action;
import com.lib.listeners.keyListener.ActionExecutor;
import com.lib.listeners.keyListener.KeyHandler;
import com.lib.standard.GamePanel;
import com.lib.standard.Panel;
import com.lib.util.Brain;
import com.properties.PanelWithProperties;
import com.properties.PropertiesPanel;
import com.properties.PropertiesStorage;
import com.settings.*;
import com.user.User;
import com.user.UserManager;

public class MainGamePanel extends Panel implements com.lib.standard.PropertiesPanel, ActionExecutor, PanelWithProperties {

    private UserManager um;
    private Properties properties;
    private PropertiesStorage ps;

    // gui
    private NumberInput[] inputs;
    private JPanel inputsPanel;
    private Label[] outputs;
    private JPanel outputsPanel;

    private Color darkBackground;
    private Color lightBackground;
    private Color foreground;
    private Button playBtn, newGameBtn, resetBtn, exitBtn;
    private Label mainOutput, funcOutput;
    private JPanel programOutputsPanel;
    private JPanel buttonsPanel;
    private Button openPropertiesBtn;

    public MainGamePanel () {
        loadSettings();

        initComp();
        syncGuiWithProperties(this.properties);
        setLSD();
        setCompTheme();
        listen();
        addComp();

        initUserMan();
    }

    public void exit (ActionEvent ae) {
        ps.writeProperties();
        System.exit(0);
    }

    private void loadSettings () {
        ps = new PropertiesStorage(this);
        ps.loadProperties();
    }

    @Override
    public void syncGuiWithProperties (Properties p) {
        if (p.get("darkTheme").equals("true")) {
            darkBackground = Values.Colors.DARK_BACKGROUND1;
            lightBackground = Values.Colors.LIGHT_BACKGROUND1;
            foreground = Color.WHITE;
        } else {
            darkBackground = Values.Colors.DARK_BACKGROUND2;
            lightBackground = Values.Colors.LIGHT_BACKGROUND2;
            foreground = Color.BLACK;
        }
    }

    @Override
    public void setCompTheme() {

        setBackground(lightBackground);
        setForeground(foreground);

        programOutputsPanel.setBackground(lightBackground);
        buttonsPanel.setBackground(lightBackground);
        inputsPanel.setBackground(lightBackground);
        outputsPanel.setBackground(lightBackground);

        openPropertiesBtn.setBackground(darkBackground);
        exitBtn.setBackground(darkBackground);
        resetBtn.setBackground(darkBackground);
        newGameBtn.setBackground(darkBackground);
        playBtn.setBackground(darkBackground);

        openPropertiesBtn.setForeground(foreground);
        exitBtn.setForeground(foreground);
        resetBtn.setForeground(foreground);
        newGameBtn.setForeground(foreground);
        playBtn.setForeground(foreground);

        for (int i = 0; i < inputs.length; i++) {
            inputs[i].setBackground(darkBackground);
            inputs[i].setForeground(foreground);

            outputs[i].setBackground(darkBackground);
            outputs[i].setForeground(foreground);
        }

        mainOutput.setBackground(darkBackground);
        mainOutput.setForeground(foreground);

        funcOutput.setBackground(darkBackground);
        funcOutput.setForeground(foreground);

    }

    private void initUserMan () {
        um = new UserManager(inputs, outputs);
    }
    
    public void newGame(ActionEvent ae) {
        int ans = JOptionPane.showConfirmDialog(this, "are you sure ?");
        if (ans!=JOptionPane.YES_OPTION)
            return;

    	Main.setContentPane(new GetSettingsPanel());
    }

    public void play (ActionEvent ae) {
        boolean success = um.play();
        if (!success)
        	return;

        for (User u : um.getUsers()) {
            int val = u.getValue();
            if (val==Settings.secretNum) {
                won(u);
            }
        }
        
        mainOutput.setText(um.getNearest()+"");
        inputs[0].requestFocus();

        updateFunctions();
    }

    public void won (User winner) {
        playBtn.setText("exit app");
        playBtn.addActionListener(ae -> {
            System.exit(0);
        });

        JOptionPane.showMessageDialog(this,
                "User "+winner.getID()+", You won, with the value: "+winner.getValue());
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

    public void openProperties (ActionEvent ae) {
        Frame f = new Frame("properties");
        PropertiesPanel pp = new PropertiesPanel(properties, f, this);
        f.setContentPane((GamePanel) pp);
        f.setSize(300, 400);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }

    public void updateFunctions () {
        if (!Settings.showFuncs)
            return;

        Brain b = um.getB();

        String funcState = b.isAllLess() ? "small" : b.isAllMore() ? "big" : "mixed";
        funcOutput.setText(funcState);
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
        newGameBtn = new Button("back");
        buttonsPanel = new JPanel();
        resetBtn = new Button("reset all");
        funcOutput = new Label();
        programOutputsPanel = new JPanel();
        openPropertiesBtn = new Button("settings");
        exitBtn = new Button("exit");
    }

    @Override
    public void setLSD() {
        setName("MainGame");
        setLayout(new GridLayout());
        setSize(new Dimension(1000, 800));

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

        programOutputsPanel.setLayout(new VerticalFlowLayout());

    }

    @Override
    public void listen() {
        playBtn.addActionListener(this::play);
        newGameBtn.addActionListener(this::newGame);
        resetBtn.addActionListener(this::reset);
        openPropertiesBtn.addActionListener(this::openProperties);
        exitBtn.addActionListener(this::exit);
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

        programOutputsPanel.add(mainOutput);
        programOutputsPanel.add(funcOutput);
        add(programOutputsPanel);
        
        buttonsPanel.add(playBtn);
        buttonsPanel.add(newGameBtn);
        buttonsPanel.add(resetBtn);
        buttonsPanel.add(openPropertiesBtn);
        buttonsPanel.add(exitBtn);
        
        add(buttonsPanel);
    }

    @Override
	public void exec(Action a) {
		switch (a) {
		case PLAY:
			play(null);
			break;
			
		case BACK:
			newGame(null);
			break;
			
		case RESET:
            reset(null);
			break;
			
		}
		
	}

    @Override
    public void setProperties(Properties p) {
        properties = p;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void writeProperties() {
        ps.writeProperties();
    }

}
