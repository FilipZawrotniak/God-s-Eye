package com.KNAB;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        timeAndDatePanel.setPreferredSize(new Dimension(this.getWidth(), (int)(this.getHeight()*0.1)));
        timeAndDatePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timeAndDatePanel.setBackground(Color.GRAY.darker());
        GridLayout layout = new GridLayout(1, 4);
        timeAndDatePanel.setLayout(layout);

        prepareTimeAndDate(timeAndDatePanel);
        return timeAndDatePanel;
    }

    private JPanel prepareButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(this.getWidth(), (int)(this.getHeight()*0.9)));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        GridLayout layout = new GridLayout(3, 3);
        layout.setHgap(20);
        layout.setVgap(20);
        buttonPanel.setLayout(layout);

        String[] buttonTitles = {"Speak", "Move", "Alarm", "button1", "button2", "Games", "Settings", "Ebook", "Exit"};
        prepareButtons(buttonPanel, buttonTitles);
        return buttonPanel;
    }

    private void prepareTimeAndDate(JPanel panel) {
        ArrayList<JLabel> labels = new ArrayList<>();

        ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("Images/KNABLogo.jpg")).getImage().getScaledInstance
                ( 50, 50, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel(icon);
        logo.setText("God's Eye");
        JLabel time = new JLabel();
        JLabel date = new JLabel();

        labels.add(logo);
        labels.add(time);
        labels.add(date);

        Font font = new Font(Font.SERIF, Font.BOLD, 20);

        for (JLabel tempLabel : labels){
            tempLabel.setForeground(Color.WHITE.darker());
            tempLabel.setBackground(Color.GRAY.darker());
            tempLabel.setFont(font);
            tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(tempLabel);
        }

        Timer timer = new Timer(22, e -> {
            time.setText(Presenter.prepareTime());
            date.setText(Presenter.prepareDate());
        });
        timer.start();
    }

    private void prepareButtons(JPanel panel, String[] buttonTitles) {
        Font font = new Font(Font.DIALOG, Font.PLAIN, 22);

        for (int i = 0; i < buttonTitles.length; i++) {
            MyButton tempButton = new MyButton(buttonTitles[i]);
            tempButton.setFont(font);
            tempButton.addMouseListener(mouseHandler.getMouseAdapter());
            tempButton.addActionListener(mouseHandler.getActionListener());
            panel.add(tempButton);
        }
    }
}
