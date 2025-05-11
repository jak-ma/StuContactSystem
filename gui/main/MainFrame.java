package gui.main;

import javax.swing.*;
import java.awt.*;
import gui.panel.SearchPanel;
import gui.panel.GroupPanel;
import gui.panel.StudentTablePanel;
import gui.panel.ButtonPanel;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("学生通信录管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // 顶部搜索栏
        add(new SearchPanel(), BorderLayout.NORTH);
        // 左侧分组树
        add(new GroupPanel(), BorderLayout.WEST);
        // 右侧学生表格
        add(new StudentTablePanel(), BorderLayout.CENTER);
        // 底部按钮栏
        add(new ButtonPanel(), BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}