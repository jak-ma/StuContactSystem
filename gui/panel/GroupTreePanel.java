package gui.panel;

import javax.swing.*;
import java.awt.*;

public class GroupTreePanel extends JPanel {

    // Placeholder for JTree component
    private JTree groupTree;

    public GroupTreePanel() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // Initialize JTree (actual data/model will be added later)
        // For now, just a placeholder label
        JLabel placeholderLabel = new JLabel("分组树区域");
        placeholderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(placeholderLabel, BorderLayout.CENTER);

        // Example: Adding a scroll pane for the tree if it becomes large
        // JScrollPane scrollPane = new JScrollPane(groupTree);
        // add(scrollPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(150, 0)); // Set preferred width, height will be managed by BorderLayout
    }

    public JTree getGroupTree() {
        return groupTree;
    }
}