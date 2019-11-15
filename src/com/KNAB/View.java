package com.KNAB;

import com.KNAB.Images.MyButton;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class View extends JFrame{
    MouseHandler mouseHandler = new MouseHandler();
//    final static public Map<String, JPanel> scenes;
    private JPanel timeAndDatePanel;
    private JPanel buttonPanel;

    public View() {
        setFrame();
//        scenes = new HashMap<>(this.getScenes());
        addComponentsToPane(this.getContentPane());
        this.setVisible(true);
    }

    public JPanel setScene(JPanel newPanel){
        if(newPanel != null){
            this.getContentPane().removeAll();
            this.addComponentsToPane(this.getContentPane());
            this.invalidate();
            this.validate();
            return newPanel;
        }
        return null;
    }

    private void setFrame() {
        this.setTitle("God's Eye");
        this.setIconImage(new ImageIcon(getClass().getResource("Images/EyeIcon.jpg")).getImage());
        this.setSize(800, 600);
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize(screenSize);
//        this.getContentPane().setCursor(getTransparentCursor());
//        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    private HashMap<String, JPanel> getScenes(){
//        Map<String, JPanel> tempHashMap = new HashMap<>();
//        tempHashMap.put("Speak", new ExitScene());
//
//        return tempHashMap;
//    }

    private void addComponentsToPane(Container pane){
        pane.add(prepareTimeAndDatePanel(), BorderLayout.PAGE_START);
        pane.add(prepareButtonPanel(), BorderLayout.CENTER);
    }

    private JPanel prepareTimeAndDatePanel(){
        timeAndDatePanel = new JPanel() ;
        prepareTimeAndDate(timeAndDatePanel);
        return timeAndDatePanel;
    }

    private JPanel prepareButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setSize(new Dimension(this.getWidth(), this.getHeight()-150));
        String[] buttonTitles = {"Speak", "Move", "Alarm", "button1", "button2", "Games", "Settings", "Ebook", "Exit"};
        prepareButtons(buttonPanel, buttonTitles);
        return buttonPanel;
    }

    private void prepareTimeAndDate(JPanel panel) {
        JLabel logo = new JLabel("God's Eye");
        JLabel time = new JLabel();
        JLabel date = new JLabel();
        Timer timer = new Timer(22, e -> {
            time.setText(Presenter.prepareTime());
            date.setText(Presenter.prepareDate());
        });
        timer.start();
        panel.add(logo);
        panel.add(time);
        panel.add(date);
    }

    private void prepareButtons(JPanel panel, String[] buttonTitles) {
        for (int i = 0; i < buttonTitles.length; i++) {
            MyButton tempButton = new MyButton(buttonTitles[i]);
            ///TODO: "panel.getWidth()/3 - 10" is hardcoded
            tempButton.setPreferredSize(new Dimension((panel.getWidth()/3) - 10,(panel.getHeight()/3)));
            tempButton.addMouseListener(mouseHandler.getMouseAdapter());
            tempButton.addActionListener(mouseHandler.getActionListener());
            panel.add(tempButton);
        }
    }
}
