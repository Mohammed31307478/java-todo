package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Windowtodo extends JFrame {
    //private ArrayList<String> toDoList = new ArrayList<>(); // Hier wordt de lijst aangemaakt

    public Windowtodo() {


        setSize(1000, 800);
        JPanel welkom = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //  welkom.setLayout(new BorderLayout());

        JLabel titel = new JLabel("welkom op mijjn to do lijst");
        welkom.add(titel);

        welkom.setBackground(new Color(122, 55, 255));

      welkom.add(new Text());

        add(welkom);
        setVisible(true);



    }

}
