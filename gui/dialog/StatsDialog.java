package gui.dialog;

import javax.swing.*;
import java.awt.*;
import service.StudentService;
import service.GroupService;
import gui.MainFrame;

public class StatsDialog extends JDialog {

    private StudentService studentService;
    private GroupService groupService;

    public StatsDialog(MainFrame parent, StudentService studentService, GroupService groupService) {
        super(parent, "统计信息", true);
        this.studentService = studentService;
        this.groupService = groupService;

        initComponents();
        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // 创建显示统计信息的组件
        JTextArea statsTextArea = new JTextArea();
        statsTextArea.setEditable(false);
        statsTextArea.setMargin(new Insets(10, 10, 10, 10));

        // // 获取并显示统计数据
        // StringBuilder statsBuilder = new StringBuilder();
        // statsBuilder.append("学生总数: ").append(studentService.getTotalStudentCount()).append("\n");
        // statsBuilder.append("分组总数: ").append(groupService.getTotalGroupCount()).append("\n");
        // // TODO: 添加更多统计信息，例如各组学生数量等

        // statsTextArea.setText(statsBuilder.toString());

        // add(new JScrollPane(statsTextArea), BorderLayout.CENTER);

        // 关闭按钮
        JButton closeButton = new JButton("关闭");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}