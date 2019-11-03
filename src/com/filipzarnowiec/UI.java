package com.filipzarnowiec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UI {

    private JFrame mainFrame;
    private JPanel panel1;
    private JButton calculatorButton;
    private JButton speakButton;
    private JButton photoGalleryButton;
    private JButton moveButton;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton settingsButton;
    private JButton exitButton;

    public UI() {
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("God's Eye");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(controlPanel);

        mainFrame.setVisible(true);
    }
}

