package com.KNAB;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler {
    private MouseAdapter mouseAdapter;
    private ActionListener actionListener;
    private Thread buttonSelect = null;
    private int loadingTime = 18;

    MouseHandler() {

        this.mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonSelect = new Thread(){
                    @Override
                    public void run() {
                        try{
                            if(e.getComponent().isEnabled()){
                                Thread.sleep(100);
                                for(int i = 0; i <= 100; i++){
                                    ((MyButton) e.getComponent()).setLoadingStatus(i);
                                    Thread.sleep(loadingTime);
                                    if(this.isInterrupted()){
                                        ((MyButton) e.getComponent()).resetLoadingStatus();
                                        this.interrupt();
                                    }
                                }
                            }
                            ((MyButton)e.getComponent()).doClick();
                            ((MyButton) e.getComponent()).resetLoadingStatus();

                        }catch (InterruptedException ignored){
                            ((MyButton) e.getComponent()).resetLoadingStatus();
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
            System.out.println(e.getActionCommand()); ///TODO: DEBUG
            //Presenter.setScene(e.getActionCommand());
        };
    }

    MouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }

    ActionListener getActionListener(){
        return actionListener;
    }

    int getLoadingTime(){ return loadingTime; }

    void setLoadingTime(int loadingTime){ this.loadingTime = loadingTime; }
}

