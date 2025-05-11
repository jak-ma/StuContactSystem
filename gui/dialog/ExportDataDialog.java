package gui.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.Student;
import service.StudentService;
import gui.MainFrame;

public class ExportDataDialog extends JDialog {

    private StudentService studentService;
    private MainFrame mainFrame;

    public ExportDataDialog(MainFrame owner, StudentService studentService) {
        super(owner, "导出数据", true);
        this.mainFrame = owner;
        this.studentService = studentService;

        initComponents();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel infoLabel = new JLabel("将学生数据导出为 CSV 文件。");
        JButton exportButton = new JButton("选择导出位置");

        panel.add(infoLabel);
        panel.add(exportButton);

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportData();
            }
        });

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("关闭");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void exportData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择导出文件位置");
        fileChooser.setSelectedFile(new File("students.csv")); // 默认文件名

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(fileToSave)) {
                // 写入 CSV 头部
                writer.append("ID,姓名,电话,邮箱,分组ID\n");

                List<Student> students = studentService.getAllStudents();
                if (students != null) {
                    for (Student student : students) {
                        writer.append(String.valueOf(student.getId()));
                        writer.append(',');
                        writer.append(student.getName());
                        writer.append(',');
                        writer.append(student.getPhone());
                        writer.append(',');
                        writer.append(student.getEmail());
                        writer.append(',');
                        writer.append(String.valueOf(student.getGroupId()));
                        writer.append('\n');
                    }
                }
                JOptionPane.showMessageDialog(this, "数据导出成功到: " + fileToSave.getAbsolutePath(), "导出成功", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "导出数据失败: " + ex.getMessage(), "导出错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}