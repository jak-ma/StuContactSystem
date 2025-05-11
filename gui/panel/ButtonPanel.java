package gui.panel;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    public ButtonPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("添加");
        JButton exportButton = new JButton("导出");
        JButton statButton = new JButton("统计");
        add(addButton);
        add(exportButton);
        add(statButton);
    }
}