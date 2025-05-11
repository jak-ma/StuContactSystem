package gui.panel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import model.Group;
import java.util.List;
import service.GroupService;
import service.StudentService;
import java.awt.*;

public class GroupTreePanel extends JPanel {

    private GroupService groupService;
    private StudentService studentService;

    // JTree 组件的占位符
    private JTree groupTree;

    public GroupTreePanel(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // 使用 GroupService 中的数据初始化 JTree
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("所有分组");
        groupTree = new JTree(root);
        loadGroupData(root);

        JScrollPane scrollPane = new JScrollPane(groupTree);
        add(scrollPane, BorderLayout.CENTER);

        // 示例：如果树变得很大，则为其添加滚动窗格
        // JScrollPane scrollPane = new JScrollPane(groupTree);
        // add(scrollPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(150, 0)); // 设置首选宽度，高度将由 BorderLayout 管理
    }

    public JTree getGroupTree() {
        return groupTree;
    }

    public void refreshTreeData() {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) groupTree.getModel().getRoot();
        root.removeAllChildren(); // 清除旧的节点
        loadGroupData(root);
    }

    private void loadGroupData(DefaultMutableTreeNode root) {
        List<Group> groups = groupService.getAllGroups();
        if (groups != null) {
            for (Group group : groups) {
                DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(group.getName());
                root.add(groupNode);
                // TODO: 如果有子分组或者学生，可以在这里添加
            }
        }
        // 更新树模型
        ((DefaultTreeModel) groupTree.getModel()).reload();
    }
}