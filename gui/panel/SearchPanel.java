package gui.panel;

import model.Student; // 新增导入
import service.StudentService; // 新增导入

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent; // 新增导入
import java.awt.event.ActionListener; // 新增导入
import java.util.List; // 新增导入

public class SearchPanel extends JPanel {
    private JTextField searchField;
    private JButton searchButton;
    private StudentService studentService; // 新增字段
    private StudentTablePanel studentTablePanel; // 新增字段

    public SearchPanel(StudentService studentService, StudentTablePanel studentTablePanel) { // 修改构造函数
        this.studentService = studentService;
        this.studentTablePanel = studentTablePanel;

        setLayout(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        searchButton = new JButton("搜索");
        add(new JLabel("🔍姓名/电话"));
        add(searchField);
        add(searchButton);

        // 添加搜索按钮事件监听器
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText().trim();
                List<Student> searchResult = studentService.searchStudents(keyword);
                studentTablePanel.refreshTable(searchResult);
            }
        });
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}