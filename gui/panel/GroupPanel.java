package gui.panel;

import javax.swing.*;
import java.awt.*;

public class GroupPanel extends JPanel {
    public GroupPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("分组树(可折叠)", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        // 后续可添加JTree组件
        setPreferredSize(new Dimension(150, 0));
    }
}