package com.ishika.smartstudent.gui;

import javax.swing.*;

public class DashboardFrame extends JFrame {
    public DashboardFrame() {
        setTitle("SmartStudent Admin Panel");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("➕ Add Student", new StudentFormPanel());
        tabs.add("📋 View Students", new StudentTablePanel());
        tabs.add("🔍 Search / Filter", new SearchPanel());
        tabs.add("⬇️ Export to CSV", new ExportPanel());
        tabs.add("📚 Subjects / Grade", new SubjectGradePanel());

        add(tabs);
        setVisible(true);
    }
}
