package gui.panel;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

    private JButton addButton;
    private JButton exportButton;
    private JButton statsButton;

    public ButtonPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the buttons
        initComponents();
    }

    private void initComponents() {
        addButton = new JButton("添加");
        exportButton = new JButton("导出");
        statsButton = new JButton("统计");

        // Add buttons to the panel
        add(addButton);
        add(exportButton);
        add(statsButton);

        // Add action listeners (placeholders for now)
        addButton.addActionListener(e -> System.out.println("Add button clicked"));
        exportButton.addActionListener(e -> System.out.println("Export button clicked"));
        statsButton.addActionListener(e -> System.out.println("Stats button clicked"));
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getExportButton() {
        return exportButton;
    }

    public JButton getStatsButton() {
        return statsButton;
    }
}