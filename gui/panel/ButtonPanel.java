package gui.panel;

import javax.swing.*;
import java.awt.*;
import service.StudentService;

public class ButtonPanel extends JPanel {
    private final StudentService studentService;
    private final JFrame parentFrame; // 主窗口引用，用于弹窗定位

    public ButtonPanel(JFrame parentFrame, StudentService studentService) {
        this.parentFrame = parentFrame;
        this.studentService = studentService;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("添加");
        JButton exportButton = new JButton("导出");
        JButton statButton = new JButton("统计");

        add(addButton);
        add(exportButton);
        add(statButton);

        // 添加按钮事件监听，弹出添加学生弹窗
        addButton.addActionListener(e -> {
            AddStudentDialog dialog = new AddStudentDialog(parentFrame, studentService);
            dialog.setVisible(true);

            // TODO: 弹窗关闭后刷新学生列表
            // 例如调用主界面或学生表格面板的刷新方法
        });

        // 你可以根据需要为导出、统计按钮添加事件
    }
}
