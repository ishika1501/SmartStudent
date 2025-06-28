package com.ishika.smartstudent.gui;

import com.ishika.smartstudent.model.Student;
import com.ishika.smartstudent.service.AdminService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StudentTablePanel extends JPanel {
    JTable table;
    DefaultTableModel model;

    public StudentTablePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        model = new DefaultTableModel();
        table = new JTable(model);
        model.setColumnIdentifiers(new String[]{"ID", "Name", "Roll No", "Dept", "Email", "Phone", "Marks"});

        JButton refresh = new JButton("🔄 Refresh");
        refresh.addActionListener(e -> loadData());

        add(new JScrollPane(table));
        add(refresh);
        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        AdminService service = new AdminService();
        List<Student> list = service.viewAllStudents();
        for (Student s : list) {
            model.addRow(new Object[]{
                s.getId(), s.getName(), s.getRollNo(), s.getDepartment(),
                s.getEmail(), s.getPhone(), s.getMarks()
            });
        }
    }
}
