package com.KNAB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class View extends JFrame{
    protected MouseHandler mouseHandler;
//    protected static Presenter presenter;

    private JPanel mainPanel;
    private JPanel panel1;
    private JLabel topLabel;
    private JLabel date;
    private JLabel time;
    private JPanel buttonPanel;
    private JButton Speak;
    private JButton Move;
    private JButton Alarm;
    private JButton button2;
    private JButton button5;
    private JButton games;
    private JButton settings;
    private JButton Ebook;
    private JButton button1;


    public View() {
        this.mouseHandler = new MouseHandler();
        setFrame();
        this.add(mainPanel);
        prepareTimeAndDate();
        prepareButtons();
        this.setVisible(true);
    }

    private void setFrame() {
        this.setTitle("God's Eye");
        this.setIconImage(new ImageIcon(getClass().getResource("Images/EyeIcon.jpg")).getImage());
        this.setSize(800, 600);
//        this.getContentPane().setCursor(getTransparentCursor());
//        this.setExtendedState(Frame.MAXIMIZED_BOTH);
//        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    protected void prepareTimeAndDate(){
        Timer timer = new Timer(22, e -> {
            time.setText(Presenter.prepareTime());
            date.setText(Presenter.prepareDate());
        });
        timer.start();
    }

    private void prepareButtons() {
        for(Component tempButton : buttonPanel.getComponents()){
            tempButton.addMouseListener(mouseHandler.getMouseAdapter());
            ((JButton) tempButton).addActionListener(mouseHandler.getActionListener());
        }
    }

    public static Cursor getTransparentCursor() {
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0),
                "blank cursor");

        return blankCursor;

    }




    protected class MouseHandler {
        private MouseAdapter mouseAdapter;
        private ActionListener actionListener;
        private Thread buttonSelect = null;

        MouseHandler() {

            this.mouseAdapter = new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    buttonSelect = new Thread(){
                        @Override
                        public void run() {
                            try{
                                Thread.sleep(3000);
                                if(!this.isInterrupted()){
                                    ((JButton)e.getComponent()).doClick();
                                }
                            }catch (InterruptedException ignored){
                            }
                        }
                    };
                    buttonSelect.start();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(buttonSelect != null){
                        try{
                            buttonSelect.interrupt();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
            };

            this.actionListener = e -> {
                System.out.println(e.getActionCommand());
                //Presenter.setScene(e.getActionCommand());
            };
        }

        MouseAdapter getMouseAdapter() {
            return mouseAdapter;
        }

        ActionListener getActionListener(){
            return actionListener;
        }
    }
}
