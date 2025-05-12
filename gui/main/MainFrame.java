package gui.main;

import javax.swing.*;

import dao.impl.StudentFileDao;

import java.awt.*;
import gui.panel.SearchPanel;
import gui.panel.GroupPanel;
import gui.panel.StudentTablePanel;
import service.StudentService;
import service.impl.StudentServiceImpl;
import gui.panel.ButtonPanel;

public class MainFrame extends JFrame {
    private StudentService studentService;
    private StudentTablePanel studentTablePanel;
    private SearchPanel searchPanel;

    public MainFrame() {
        setTitle("学生通信录管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // 初始化服务
        studentService = new StudentServiceImpl(new StudentFileDao());

        // 初始化面板
        studentTablePanel = new StudentTablePanel(studentService);
        searchPanel = new SearchPanel(studentService, studentTablePanel); // 传递依赖

        // 顶部搜索栏
        add(searchPanel, BorderLayout.NORTH);
        // 左侧分组树
        add(new GroupPanel(), BorderLayout.WEST);
        // 右侧学生表格
        add(studentTablePanel, BorderLayout.CENTER);
        // 底部按钮栏
        add(new ButtonPanel(this, studentService), BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}