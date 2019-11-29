package com.KNAB;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Presenter {
    private static View view;
    private static Model model;

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public static void executeCommand(String command){
        switch(command){
            case "Speak!":
                model.speak(SpeakView.getValueLabel());
                break;
            case "Clear":
                SpeakView.clearLabel();
                break;
            case "Return":
                setScene("Home");
                break;
            case "Speak":
                setScene("Speak");
                break;
            case "Exit" :
                System.exit(0);
        }
    }

    private static void setScene(String key){
        try{
            view.setScene(key);
        }catch (Exception e){
            System.out.println("Smth went wrong");
            e.printStackTrace();
        }
    }

    public static String prepareTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("     HH:mm");

       return now.format(timeFormatter);
    }

    public static String prepareDate(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd / MM / yyyy");

        return now.format(dateFormatter);
    }

    public static void updateSpeakLabel(String letter){
        try{
                SpeakView.updateLabel(letter);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Presenter -> JLabel not found !");
        }
    }
}
