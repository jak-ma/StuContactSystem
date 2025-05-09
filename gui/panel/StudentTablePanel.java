package gui.panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentTablePanel extends JPanel {

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentTablePanel() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // Define column names for the table
        String[] columnNames = {"姓名", "电话", "邮箱", "分组"};

        // Create a DefaultTableModel (non-editable by default)
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make table cells non-editable
                return false;
            }
        };

        studentTable = new JTable(tableModel);

        // Add some sample data (placeholders)
        addSampleData();

        // Add the table to a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addSampleData() {
        // Sample data - this will be replaced with actual data later
        tableModel.addRow(new Object[]{"张三", "13800138000", "zhangsan@example.com", "1班"});
        tableModel.addRow(new Object[]{"李四", "13900139000", "lisi@example.com", "2班"});
        tableModel.addRow(new Object[]{"王五", "13700137000", "wangwu@example.com", "1班"});
    }

    public JTable getStudentTable() {
        return studentTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}