package com.KNAB;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler {
    private MouseAdapter mouseAdapter;
    private ActionListener executeCommandListener;
    private ActionListener updateSpeakLabelListener;
    private static Thread buttonSelect = null;
    private int loadingTime = 15;

    MouseHandler() {

        this.mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonSelect = new Thread(){
                    @Override
                    public void run() {
                        try{
                            if(e.getComponent().isEnabled()){
                                Thread.sleep(200);
                                for(int i = 0; i <= 100; i++){
                                    ((MyButton) e.getComponent()).setLoadingStatus(i);
                                    Thread.sleep(loadingTime);
                                }
                                ((MyButton) e.getComponent()).resetLoadingStatus();
                                if(e.getComponent().isValid()){
                                    ((MyButton)e.getComponent()).doClick();
                                }
                            }
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

        this.executeCommandListener = e -> {
            buttonSelect.interrupt();
            System.out.println(e.getActionCommand()); ///TODO: DEBUG
            Presenter.executeCommand(e.getActionCommand());///TODO: fixBug - button is clicked two times !
        };

        this.updateSpeakLabelListener = e -> {
            Presenter.updateSpeakLabel(e.getActionCommand());
        };
    }

    MouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }

    ActionListener getExecuteCommandListener(){
        return executeCommandListener;
    }

    ActionListener getUpdateSpeakLabelListener() { return updateSpeakLabelListener; }

    int getLoadingTime(){ return loadingTime; }

    void setLoadingTime(int loadingTime){ this.loadingTime = loadingTime; }
}

