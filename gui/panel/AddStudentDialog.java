package gui.panel;

import model.Student;
import service.StudentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 添加学生信息弹窗
 */
public class AddStudentDialog extends JDialog {

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField groupIdField;

    private final StudentService studentService;

    public AddStudentDialog(Frame owner, StudentService studentService) {
        super(owner, "添加学生", true);
        this.studentService = studentService;

        initUI();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("姓名:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("电话:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("邮箱:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("分组ID:"));
        groupIdField = new JTextField();
        panel.add(groupIdField);

        JButton addButton = new JButton("添加");
        JButton cancelButton = new JButton("取消");
        panel.add(addButton);
        panel.add(cancelButton);

        add(panel, BorderLayout.CENTER);

        // 添加按钮事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });

        // 取消按钮关闭弹窗
        cancelButton.addActionListener(e -> dispose());
    }

    private void onAdd() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String groupIdText = groupIdField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "姓名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int groupId;
        try {
            groupId = Integer.parseInt(groupIdText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "分组ID必须是整数", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = new Student();
        student.setName(name);
        student.setPhone(phone);
        student.setEmail(email);
        student.setGroupId(groupId);

        try {
            studentService.addStudent(student);
            JOptionPane.showMessageDialog(this, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            dispose();  // 关闭弹窗
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "添加失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
