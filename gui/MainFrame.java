package gui;

import javax.swing.*;
import java.awt.*;
import gui.panel.SearchPanel;
import gui.panel.GroupTreePanel;
import gui.panel.StudentTablePanel;
import gui.panel.ButtonPanel;
import gui.panel.StatusBarPanel;
import service.StudentService;
import service.GroupService;
import service.impl.StudentServiceImpl;
import service.impl.GroupServiceImpl;

public class MainFrame extends JFrame {

    private StudentService studentService;
    private GroupService groupService;
    private StudentTablePanel studentTablePanel;
    private GroupTreePanel groupTreePanel;

    public MainFrame() {
        // 初始化服务
        // studentService = new StudentServiceImpl(new StudentDao());
        // groupService = new GroupServiceImpl(new GroupDao(), new StudentDao());

        setTitle("学生通信录管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // 居中窗口
        // setResizable(false); // 可选：使窗口不可调整大小

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        // 主框架的布局
        setLayout(new BorderLayout());

        // 创建面板实例
        SearchPanel searchPanel = new SearchPanel();
        searchPanel.setPreferredSize(new Dimension(getWidth(), 50));

        this.groupTreePanel = new GroupTreePanel(groupService, studentService);
        // groupTreePanel.setPreferredSize(new Dimension(150, getHeight())); // 首选大小在 GroupTreePanel 中设置

        this.studentTablePanel = new StudentTablePanel(studentService, groupService);

        ButtonPanel buttonPanel = new ButtonPanel(studentService, groupService, this);
        buttonPanel.setPreferredSize(new Dimension(getWidth(), 50));
        StatusBarPanel statusBarPanel = new StatusBarPanel();
        // statusBarPanel.setPreferredSize(new Dimension(getWidth(), 30)); // 首选大小在 StatusBarPanel 中设置

        // 将面板添加到框架
        add(searchPanel, BorderLayout.NORTH);
        add(this.groupTreePanel, BorderLayout.WEST);
        add(this.studentTablePanel, BorderLayout.CENTER);
        
        // 创建一个底部面板来容纳 buttonPanel 和 statusBarPanel
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.CENTER);
        southPanel.add(statusBarPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // 确保UI更新在事件调度线程上完成
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }

    public void refreshStudentTable() {
        if (studentTablePanel != null) {
            studentTablePanel.refreshTableData();
        }
    }

    public void refreshGroupTree() {
        if (groupTreePanel != null) {
            groupTreePanel.refreshTreeData();
        }
    }
}