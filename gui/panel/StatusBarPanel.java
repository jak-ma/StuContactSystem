package gui.panel;

import javax.swing.*;
import java.awt.*;

public class StatusBarPanel extends JPanel {

    private JLabel statusLabel;

    public StatusBarPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        initComponents();
    }

    private void initComponents() {
        statusLabel = new JLabel("状态栏：准备就绪"); // Default status message
        statusLabel.setForeground(Color.BLACK); // Set text color for better visibility depending on background

        add(statusLabel);
        setPreferredSize(new Dimension(0, 30)); // Height of 30, width will be managed by BorderLayout
    }

    public void setStatus(String message) {
        statusLabel.setText("状态栏：" + message);
    }
}