package com.KNAB;

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
        ///TODO: text need to be showed in the center of button
        int shift = (getText().length() - 4)*4;
        FontMetrics metrics = g2.getFontMetrics(this.getFont());
        g2.drawString(getText(), (getWidth() - metrics.stringWidth(getText()))/2, (getHeight() - metrics.getHeight())/2 + metrics.getAscent());

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
