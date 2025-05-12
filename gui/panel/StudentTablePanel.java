package gui.panel;

import model.Student;
import service.StudentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentTablePanel extends JPanel {
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentService studentService;

    public StudentTablePanel(StudentService studentService) {
        this.studentService = studentService;
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "姓名", "电话", "邮箱", "分组ID"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);

        add(new JScrollPane(studentTable), BorderLayout.CENTER);
        loadInitialData();
    }

    private void loadInitialData() {
        List<Student> students = studentService.getAllStudents();
        refreshTable(students);
    }

    public void refreshTable(List<Student> students) {
        // 清空现有数据
        tableModel.setRowCount(0);
        if (students != null) {
            for (Student student : students) {
                Object[] rowData = {
                        student.getId(),
                        student.getName(),
                        student.getPhone(),
                        student.getEmail(),
                        student.getGroupId()
                };
                tableModel.addRow(rowData);
            }
        }
    }
}