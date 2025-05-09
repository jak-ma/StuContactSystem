package gui;

import javax.swing.*;
import java.awt.*;
import gui.panel.SearchPanel;
import gui.panel.GroupTreePanel;
import gui.panel.StudentTablePanel;
import gui.panel.ButtonPanel;
import gui.panel.StatusBarPanel;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("学生通信录管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        // setResizable(false); // Optional: make window not resizable

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        // Layout for the main frame
        setLayout(new BorderLayout());

        // Create panel instances
        SearchPanel searchPanel = new SearchPanel();
        searchPanel.setPreferredSize(new Dimension(getWidth(), 50));

        GroupTreePanel groupTreePanel = new GroupTreePanel();
        // groupTreePanel.setPreferredSize(new Dimension(150, getHeight())); // PreferredSize is set in GroupTreePanel

        StudentTablePanel studentTablePanel = new StudentTablePanel();

        ButtonPanel buttonPanel = new ButtonPanel();
        buttonPanel.setPreferredSize(new Dimension(getWidth(), 50));
        StatusBarPanel statusBarPanel = new StatusBarPanel();
        // statusBarPanel.setPreferredSize(new Dimension(getWidth(), 30)); // PreferredSize is set in StatusBarPanel

        // Add panels to the frame
        add(searchPanel, BorderLayout.NORTH);
        add(groupTreePanel, BorderLayout.WEST);
        add(studentTablePanel, BorderLayout.CENTER);
        
        // Create a bottom panel to hold both buttonPanel and statusBarPanel
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.CENTER);
        southPanel.add(statusBarPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // Ensure UI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}