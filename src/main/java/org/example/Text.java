
package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class Text extends JPanel {

    Dataconnect dataconnect = new Dataconnect();
    private ArrayList<Todoitems> toDoList = new ArrayList<>(); // Gebruik Todoitems in plaats van strings
    DefaultListModel<Todoitems> listModel = new DefaultListModel<>();
    JList<Todoitems> toDoListJList = new JList<>(listModel);

    public Text() {
        // GUI-componenten
        JTextField textinput = new JTextField(20);
        JButton voeginput = new JButton("toevoegen");
        JButton bewerk = new JButton("bewerken");
        JButton verwijdergeselecteerde = new JButton("geselecteerde verwijderen");
        JButton voltooid = new JButton("voltooid");
        JTextField editInput = new JTextField(20);
        JButton terug = new JButton("terug");
        JButton onvoltooid = new JButton("onvoltooid");
        JButton sorteren = new JButton("sorteren");

// zichtbaarheid van sommige knoppen
        add(onvoltooid);
        onvoltooid.setVisible(false);
        add(textinput);
        add(voeginput);
        add(new JScrollPane(toDoListJList));
        add(bewerk);
        add(verwijdergeselecteerde);
        add(voltooid);
        add(editInput);
        editInput.setVisible(false);
        add(terug);
        add(sorteren);
        terug.setVisible(false);

        // Aangepaste renderer om checkbox weer te geven
        toDoListJList.setCellRenderer(new CheckboxListCellRenderer());

// Laden van taken uit de database
        try {
            toDoList = dataconnect.getalltodos(); // Gebruik Todoitems in plaats van strings
            updateToDoItemsLabel();
        } catch (SQLException e) {
            System.out.println("Fout bij het ophalen van taken uit de database: " + e.getMessage());
        }
        // ActionListener voor toevoegen van een nieuwe taak
        voeginput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textinput.getText();
                if (!text.isEmpty()) {
                    Todoitems newItem = new Todoitems(text, false);
                    toDoList.add(newItem); // Voeg Todoitems toe aan de lijst
                    textinput.setText("");
                    updateToDoItemsLabel();
                    dataconnect.insertTodo(text, newItem);
                }
            }
        });
        // ActionListener voor verwijderen van geselecteerde taken
        verwijdergeselecteerde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = toDoListJList.getSelectedIndices();
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    Todoitems removedTask = toDoList.remove(selectedIndices[i]);
                    updateToDoItemsLabel();
                    dataconnect.deleteTodo(removedTask.getId());
                }
            }
        });
        // ActionListener voor markeren van geselecteerde taken als voltooid
        voltooid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = toDoListJList.getSelectedIndices();
                for (int selectedIndex : selectedIndices) {
                    Todoitems selectedTask = toDoList.get(selectedIndex);
                    selectedTask.setCompleted(true);
                    dataconnect.updateTodoCompletionStatus(selectedTask.getTaak(), true);
                }
                updateToDoItemsLabel();
            }
        });

        // ActionListener voor bewerken van geselecteerde taak
        bewerk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = toDoListJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Todoitems selectedTask = toDoList.get(selectedIndex);
                    String editedText = editInput.getText();
                    if (!editedText.isEmpty()) {
                        selectedTask.setTaak(editedText);
                        dataconnect.updateTodoText(selectedTask.getTaak(), editedText);

                    }
                    //instellingen van zichtbaarheid knoppen
                    textinput.setVisible(false);
                    voeginput.setVisible(false);
                    bewerk.setVisible(false);
                    verwijdergeselecteerde.setVisible(false);
                    voltooid.setVisible(false);
                    editInput.setVisible(true);
                    bewerk.setVisible(true);
                    terug.setVisible(true);
                    onvoltooid.setVisible(true);
                }
            }

        });
        // ActionListener voor terugkeren naar normale weergave
        terug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instellingen voor zichtbaarheid van knoppen

                textinput.setVisible(true);
                voeginput.setVisible(true);
                bewerk.setVisible(true);
                verwijdergeselecteerde.setVisible(true);
                voltooid.setVisible(true);
                editInput.setVisible(true);
                bewerk.setVisible(true);
                terug.setVisible(false);
                editInput.setVisible(false);
                onvoltooid.setVisible(false);
            }


        });
        // ActionListener voor markeren van geselecteerde taken als onvoltooid
        onvoltooid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = toDoListJList.getSelectedIndices();
                for (int selectedIndex : selectedIndices) {
                    Todoitems selectedTask = toDoList.get(selectedIndex);
                    selectedTask.setCompleted(!selectedTask.isCompleted());
                    dataconnect.updateTodoCompletionStatus(selectedTask.getTaak(), selectedTask.isCompleted());
                }
                updateToDoItemsLabel();
            }

        });
        // ActionListener voor sorteren van taken
        sorteren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(toDoList);
                updateToDoItemsLabel();
            }
        });

    }
    // Methode om de lijstweergave bij te werken
    private void updateToDoItemsLabel() {
        listModel.clear();
        for (Todoitems item : toDoList) {
            listModel.addElement(item);
        }

    }
}
