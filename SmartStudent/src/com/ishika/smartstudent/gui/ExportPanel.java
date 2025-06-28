package com.ishika.smartstudent.gui;

import com.ishika.smartstudent.service.AdminService;
import com.ishika.smartstudent.util.CsvExporter;

import javax.swing.*;
import java.io.IOException;

public class ExportPanel extends JPanel {
    public ExportPanel() {
        JButton btn = new JButton("Export students.csv");
        add(btn);

        btn.addActionListener(e -> {
            try {
                CsvExporter.export(new AdminService().viewAllStudents(), "students.csv");
                JOptionPane.showMessageDialog(this, "✅ Exported to students.csv");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "❌ "+ex.getMessage());
            }
        });
    }
}
