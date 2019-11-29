package com.KNAB;

import javax.swing.*;
import java.awt.*;
import java.util.*;

///TODO: clean code up
///TODO: setting layout duplicate !
///TODO: make preparePanel's methods consisted
public class SpeakView extends JPanel {
    private View view; ///TODO: Nessesary ???
    private static JPanel abcPanel;
    private JPanel functionPanel;
    private JPanel topPanel;
    private static JLabel valueLabel;

    SpeakView(View view){
        this.view = view;
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.add(prepareAbcPanel(), BorderLayout.CENTER);
        this.add(prepareFunctionPanel(), BorderLayout.EAST);
        this.add(prepareTopPanel(), BorderLayout.PAGE_START);
    }

    private JPanel prepareAbcPanel(){
        abcPanel = new JPanel();
        abcPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        abcPanel.setBackground(Color.GRAY.darker());
        GridLayout layout = new GridLayout(3 ,3);
        layout.setHgap(20);
        layout.setVgap(20);
        abcPanel.setLayout(layout);

        this.fillAbcPanel(abcPanel);

        return abcPanel;
    }

    private JPanel prepareFunctionPanel(){
        functionPanel = new JPanel();
        functionPanel.setPreferredSize(new Dimension(((int)(view.getWidth()*0.2)),view.getHeight()));
        functionPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        GridLayout layout = new GridLayout(0 ,1);
        layout.setHgap(20);
        layout.setVgap(20);
        functionPanel.setLayout(layout);

        String[] buttonsNames= {"Clear", "Frequent/saved phrases", "Speak!"};

        for(String s : buttonsNames){
            MyButton tempButton = new MyButton(s);
            if(s.equals("Frequent/saved phrases")){
                tempButton.setEnabled(false);
            }
            tempButton.addActionListener(tempButton.getMouseHandler().getExecuteCommandListener());
            functionPanel.add(tempButton);
        }

        return functionPanel;
    }

    private JPanel prepareTopPanel(){
        topPanel = new JPanel() ;
//        topPanel.setPreferredSize(new Dimension(view.getWidth(), (int)(view.getHeight()*0.2)));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topPanel.setPreferredSize(new Dimension(view.getWidth(), ((int) (view.getHeight()*0.2))));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        GridLayout layout = new GridLayout();

        topPanel.setLayout(layout);
        layout.setHgap(5);
        layout.setVgap(5);
        topPanel.setLayout(new GridLayout(1,2));

        JPanel returnPanel = new JPanel();
        layout.setHgap(15);
        layout.setVgap(15);
        returnPanel.setLayout(layout);
        topPanel.add(returnPanel);

        MyButton returnButton = new MyButton("Return");
        returnButton.addActionListener(returnButton.getMouseHandler().getExecuteCommandListener());
        returnPanel.add(returnButton);

        layout = new GridLayout(2,1);
        JPanel tempPanel = new JPanel(layout);
        returnPanel.add(tempPanel);

        Font font = new Font(Font.SERIF, Font.BOLD, 15);

        JLabel titleLabel = new JLabel("God's Eye:");
        titleLabel.setBackground(Color.GRAY.darker());
        titleLabel.setFont(font);
        tempPanel.add(titleLabel);
        JLabel titleLabel2 = new JLabel("Speak");
        titleLabel2.setFont(font);
        titleLabel2.setBackground(Color.GRAY.darker());
        tempPanel.add(titleLabel2);

        valueLabel = new JLabel("");
        font = new Font(Font.MONOSPACED, Font.ITALIC, 50);
        valueLabel.setFont(font);
        valueLabel.setBackground(Color.LIGHT_GRAY);
        valueLabel.setOpaque(true);
        topPanel.add(valueLabel);

        return topPanel;
    }

    private void fillAbcPanel(JPanel panel){
        LinkedHashMap<String, Character[]> alphabetDictionary = new LinkedHashMap<>(getAlphabetDic());
        System.out.println(this);

        if(panel != null){
            panel.removeAll();
        }else {
            return;
        }

        int i = 0;
        for(String tempButtonName : alphabetDictionary.keySet()){
            Font font = new Font(Font.DIALOG, Font.PLAIN, 20);

            MyButton tempButton = new MyButton(tempButtonName);
            tempButton.setFont(font);
            tempButton.setPosition(i);
            tempButton.addActionListener(e -> {
                int position = tempButton.getPosition();
                panel.removeAll();
                setCharacterButtons(position, alphabetDictionary.get(tempButtonName), panel);
                view.revalidate();
                view.repaint();
            });
            panel.add(tempButton);
            i++;

        }
        panel.revalidate();
        panel.repaint();
    }

    private void setCharacterButtons(int position, Character[] characters, JPanel panel){
        //assuming gridPane is 3x3
        int j = 0;
        ///TODO: place it in the way that letters show up in clock order
        for(int i = 0; i < 9; i++){
            if(position == i){
                panel.add(this.getCharacterButton(""));
                continue;
            }
            if(position - 3 == i){
                panel.add(this.getCharacterButton(characters[j++].toString()));
                continue;
            }
            if(position + 3 == i){
                panel.add(this.getCharacterButton(characters[j++].toString()));
                continue;
            }
            if(position + 1 == i && !((position+1)%3 == 0)){
                panel.add(this.getCharacterButton(characters[j++].toString()));
                continue;
            }
            if(position - 1 == i && !((position%3 == 0))){
                panel.add(this.getCharacterButton(characters[j++].toString()));
                continue;
            }
            if (position == 7 && i == 3){
                panel.add(this.getCharacterButton(characters[j++].toString()));
                continue;
            }
            panel.add(new JPanel());
        }
    }

    private static Map<String, Character[]> getAlphabetDic(){
        Map<String, Character[]> tempMap= new LinkedHashMap<>();
        String[] headers = {"AB", "CDE", "FG", "HIJ", "KLMN", "OPQ", "RS", "TUWV", "XYZ"};

        for(String s : headers){
            Character[] characters = new Character[s.length()];
            for(int i = 0; i < s.length(); i++){
                characters[i] = (s.toCharArray())[i];
            }
            tempMap.put(s, characters);
        }
        return tempMap;
    }

    private MyButton getCharacterButton(String name){
        Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
        MyButton tempButton = new MyButton(name);
        tempButton.setFont(font);

        tempButton.addActionListener(e -> {
            Presenter.updateSpeakLabel(e.getActionCommand());
            fillAbcPanel(abcPanel);
        });

        return tempButton;
    }

    static void updateLabel(String letter){
        valueLabel.setText(valueLabel.getText() + letter);
    }

    static void clearLabel(){
        valueLabel.setText("");
    }

    static String getValueLabel(){
        return valueLabel.getText();
    }
}
