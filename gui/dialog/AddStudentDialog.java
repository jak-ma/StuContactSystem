package gui.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Student;
import model.Group;
import service.StudentService;
import service.GroupService;
import gui.MainFrame;
import java.util.List;

public class AddStudentDialog extends JDialog {

    private StudentService studentService;
    private GroupService groupService;
    private MainFrame mainFrame;

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JComboBox<String> groupComboBox;

    public AddStudentDialog(MainFrame owner, StudentService studentService, GroupService groupService) {
        super(owner, "添加学生", true);
        this.mainFrame = owner;
        this.studentService = studentService;
        this.groupService = groupService;

        initComponents();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // 姓名
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("姓名:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        // 电话
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("电话:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(20);
        formPanel.add(phoneField, gbc);

        // 邮箱
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("邮箱:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        formPanel.add(emailField, gbc);

        // 分组
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("分组:"), gbc);
        gbc.gridx = 1;
        groupComboBox = new JComboBox<>();
        loadGroupData();
        formPanel.add(groupComboBox, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("保存");
        JButton cancelButton = new JButton("取消");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStudent();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadGroupData() {
        List<Group> groups = groupService.getAllGroups();
        if (groups != null) {
            for (Group group : groups) {
                groupComboBox.addItem(group.getName());
            }
        }
    }

    private void saveStudent() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String groupName = (String) groupComboBox.getSelectedItem();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "姓名和电话不能为空!", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Group selectedGroup = groupService.findGroupByName(groupName);
        if (selectedGroup == null) {
             // 如果没有选择分组或者分组不存在，可以创建一个默认分组或者提示用户
            JOptionPane.showMessageDialog(this, "请选择一个有效的分组!", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ID 通常由数据库或服务层生成，这里暂时设为0或由服务处理
        Student newStudent = new Student(0, name, phone, email, selectedGroup.getId());
        studentService.addStudent(newStudent);

        // 刷新主界面的学生列表和分组树
        mainFrame.refreshStudentTable();
        mainFrame.refreshGroupTree();

        JOptionPane.showMessageDialog(this, "学生添加成功!", "成功", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}