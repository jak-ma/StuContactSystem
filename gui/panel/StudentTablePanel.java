package gui.panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Student;
import service.StudentService;
import service.GroupService; // 添加 GroupService 导入
import model.Group; // 添加 Group 导入
import java.util.List;
import java.awt.*;

public class StudentTablePanel extends JPanel {

    private StudentService studentService;
    private GroupService groupService; // 添加 GroupService 成员变量

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentTablePanel(StudentService studentService, GroupService groupService) { // 修改构造函数以接收 GroupService
        this.studentService = studentService;
        this.groupService = groupService; // 初始化 GroupService
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // 定义表格的列名
        String[] columnNames = {"姓名", "电话", "邮箱", "分组"};

        // 创建一个 DefaultTableModel（默认不可编辑）
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // 使表格单元格不可编辑
                return false;
            }
        };

        studentTable = new JTable(tableModel);

        // TODO: 使用 studentService 加载实际数据
        loadStudentData();

        // 将表格添加到 JScrollPane 以使其可滚动
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);
    }



    public JTable getStudentTable() {
        return studentTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void refreshTableData() {
        loadStudentData();
    }

    private void loadStudentData() {
        // 清空现有数据
        tableModel.setRowCount(0);
        List<Student> students = studentService.getAllStudents();
        if (students != null) {
            for (Student student : students) {
                String groupName = "未分组"; // 默认值
                if (student.getGroupId() != 0) { // 假设 0 表示未分组或无效分组ID
                    Group group = groupService.findGroupById(student.getGroupId()); // 假设 GroupService 有 findGroupById 方法
                    if (group != null) {
                        groupName = group.getName();
                    }
                }
                tableModel.addRow(new Object[]{student.getName(), student.getPhone(), student.getEmail(), groupName});
            }
        }
    }
}