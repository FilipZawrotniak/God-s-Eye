package com.KNAB;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Presenter {
    private View view;
    private Model model;
    private static Map<String, JPanel> scenes; ///TODO make it final !

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
        this.scenes = new HashMap<>();

    }

    public static JPanel setScene(String key){
        if(scenes.containsKey(key)){
            return scenes.get(key);
        }else
            return null;
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
}
