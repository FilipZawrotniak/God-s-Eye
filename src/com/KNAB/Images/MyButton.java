package com.KNAB.Images;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    private int loadingStatus = 0;

    public MyButton(String text) {
        super(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setPaint(new GradientPaint(new Point(0,0), Color.WHITE, new Point(0, getWidth()), Color.GRAY.darker()));
        g2.fillRoundRect(loadingStatus, 0, getWidth(), getHeight(), 0, 30);

        g2.setPaint(new GradientPaint(new Point(0,0), Color.WHITE, new Point(0, getWidth()), Color.BLUE.darker()));
        g2.fillRoundRect(0, 0, loadingStatus, getHeight(), 30, 0);

        g2.setPaint(Color.BLACK);
        g2.setFont(Font.getFont("Monaco"));
        g2.drawString(getText(), getWidth()/2 - 20, getHeight()/2);

        g2.dispose();
    }

    public int getLoadingStatus() {
        return loadingStatus;
    }

    public void setLoadingStatus(int loadingStatus) {
        if(loadingStatus > 0 && loadingStatus <= 100){
            this.loadingStatus = (getWidth()*loadingStatus)/100;
        }else if(loadingStatus == 0){
            resetLoadingStatus();
        }else{
            throw new IndexOutOfBoundsException("LoadingStatus has to be in range [0,100]");
        }
        this.repaint();
    }

    public void resetLoadingStatus(){
        this.loadingStatus = 0;
    }
}
