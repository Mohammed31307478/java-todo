package org.example;

import javax.swing.*;
import java.awt.*;

public class CheckboxListCellRenderer extends JPanel implements ListCellRenderer<Todoitems> {
    private JCheckBox checkBox = new JCheckBox();
    private JLabel label = new JLabel();

    public CheckboxListCellRenderer() {
        setLayout(new BorderLayout());
        add(checkBox, BorderLayout.WEST);
        add(label, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Todoitems> list, Todoitems value, int index, boolean isSelected, boolean cellHasFocus) {
        setEnabled(list.isEnabled());

        // Als het item is voltooid, voeg een vinkje toe aan de checkbox en aan de tekst
        if (value.isCompleted()) {
            checkBox.setSelected(true);
            label.setText(value.getTaak() + " \u2713"); // Voeg het vinkje toe aan de tekst
        } else {
            checkBox.setSelected(isSelected); // Pas de checkbox aan op basis van de selectiestatus
            label.setText(value.getTaak()); // Behoud de oorspronkelijke tekst voor niet-voltooide taken
        }

        // Pas de achtergrondkleur aan op basis van de selectiestatus
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
