package gui.panel;

import javax.swing.*;
import gui.MainFrame;
import gui.dialog.AddStudentDialog;
import gui.dialog.ExportDataDialog;
import gui.dialog.StatsDialog;
import service.StudentService;
import service.GroupService;
import java.awt.*;

public class ButtonPanel extends JPanel {

    private StudentService studentService;
    private GroupService groupService;
    private MainFrame mainFrame;

    private JButton addButton;
    private JButton exportButton;
    private JButton statsButton;

    public ButtonPanel(StudentService studentService, GroupService groupService, MainFrame mainFrame) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout(FlowLayout.CENTER)); // 居中按钮
        initComponents();
    }

    private void initComponents() {
        addButton = new JButton("添加");
        exportButton = new JButton("导出");
        statsButton = new JButton("统计");

        // 将按钮添加到面板
        add(addButton);
        add(exportButton);
        add(statsButton);

        // 添加动作监听器（目前为占位符）
        // TODO: 使用服务和 mainFrame 实现按钮的实际操作
        addButton.addActionListener(e -> {
            // 打开添加学生对话框
            AddStudentDialog addStudentDialog = new AddStudentDialog(mainFrame, studentService, groupService);
            addStudentDialog.setVisible(true);
        });
        exportButton.addActionListener(e -> {
            // 打开导出数据对话框
            ExportDataDialog exportDataDialog = new ExportDataDialog(mainFrame, studentService);
            exportDataDialog.setVisible(true);
        });
        statsButton.addActionListener(e -> {
            // 打开统计信息对话框
            StatsDialog statsDialog = new StatsDialog(mainFrame, studentService, groupService);
            statsDialog.setVisible(true);
        });
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