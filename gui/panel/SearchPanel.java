package gui.panel;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    private JTextField searchField;
    private JButton searchButton;

    public SearchPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
        initComponents();
    }

    private void initComponents() {
        JLabel searchLabel = new JLabel("搜索栏：");
        searchField = new JTextField(20); // Set preferred width for the text field
        searchButton = new JButton("🔍 姓名/电话"); // Using a Unicode character for the icon

        // Add components to the panel
        add(searchLabel);
        add(searchField);
        add(searchButton);

        // Add action listener for the search button (placeholder for now)
        searchButton.addActionListener(e -> {
            // Placeholder for search logic
            String searchText = searchField.getText();
            System.out.println("Search initiated for: " + searchText);
            //JOptionPane.showMessageDialog(this, "搜索内容: " + searchText, "搜索提示", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}