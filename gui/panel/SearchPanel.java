package gui.panel;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    private JTextField searchField;
    private JButton searchButton;

    public SearchPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        searchButton = new JButton("搜索");
        add(new JLabel("🔍姓名/电话"));
        add(searchField);
        add(searchButton);
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}