package com.filipzarnowiec;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

//https://www.tutorialspoint.com/swing/swing_jpanel.htm

public class View {

    //test
    //private static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    private JFrame mainFrame = new JFrame();
    private JPanel panel1;

    ///TODO: fix bug - image is not displaying correctly
    //ImageIcon logo = new ImageIcon(this.getClass().getResource("Images/KNABLogo.jpg"));
    //private JLabel logoLabel = new JLabel(logo);

    private JButton calculatorButton = new JButton("Calculator");
    private JButton exitButton = new JButton("Exit");
    private JButton speakButton;
    private JButton photoGalleryButton;
    private JButton moveButton;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton settingsButton;


    public View() {
        prepareGUI();
    }

    private void prepareGUI(){

        mainFrame = new JFrame("God's Eye");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        //mainFrame.setUndecorated(true);

        ///test
        //device.setFullScreenWindow(mainFrame);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        exitButton.addMouseListener(new myMouseListener());
        calculatorButton.addMouseListener(new myMouseListener());

        //controlPanel.add(logoLabel);
        controlPanel.add(exitButton);
        controlPanel.add(calculatorButton);

///
//        mainFrame.getContentPane().setCursor(getTransparentCursor());

        mainFrame.add(controlPanel);

        mainFrame.setVisible(true);

    }

    private class myMouseListener implements MouseListener{

        private Object focusTarget = null;

        public myMouseListener() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("clicked !" + e.getSource());
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ///TODO: add animation for checking a button ( SUGG: 0,7 s delay 2,3 s animation)
            focusTarget = e.getSource();

            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println("I'm going to sleep");
                        Thread.sleep(3000);
                        if(focusTarget != null && focusTarget.equals(e.getSource())){
                            mouseClicked(e);
                        }
                    }catch (InterruptedException event){
                        ///"we dont care about this"
                    }

                }
            };

            new Thread(task).start();

        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(focusTarget == e.getSource()){
                System.out.println("null again");
                focusTarget = null;
            }
        }
    }

    public static Cursor getTransparentCursor(){
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

// Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");

// Set the blank cursor to the JFrame.
        return blankCursor;
    }



}

