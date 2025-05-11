package gui.panel;

import javax.swing.*;
import java.awt.*;

public class StudentTablePanel extends JPanel {
    public StudentTablePanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("学生列表表格", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        // 后续可添加JTable组件
    }
}