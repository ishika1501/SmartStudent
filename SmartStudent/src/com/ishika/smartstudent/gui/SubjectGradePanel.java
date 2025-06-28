package com.ishika.smartstudent.gui;

import com.ishika.smartstudent.model.Subject;
import com.ishika.smartstudent.service.SubjectService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SubjectGradePanel extends JPanel {
    private final JTextField rollField = new JTextField(10);
    private final JTextArea resultArea = new JTextArea(15, 70);
    private final SubjectService service = new SubjectService();

    public SubjectGradePanel() {
        setLayout(new BorderLayout());

        // Top Panel for input
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Roll No:"));
        inputPanel.add(rollField);
        JButton loadBtn = new JButton("Load Subjects");
        inputPanel.add(loadBtn);

        JButton addBtn = new JButton("Add Subject Marks");
        inputPanel.add(addBtn);

        // Result Area
        resultArea.setEditable(false);
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Load subjects
        loadBtn.addActionListener(e -> {
            String roll = rollField.getText().trim();
            if (!roll.isEmpty()) {
                List<Subject> list = service.getSubjectsByRollNo(roll);
                double avg = list.stream().mapToInt(Subject::getMarks).average().orElse(0);
                String grade = getGrade(avg);

                resultArea.setText("Subject Marks for Roll No: " + roll + "\n");
                for (Subject s : list) {
                    resultArea.append(s.getSubjectName() + ": " + s.getMarks() + "\n");
                }
                resultArea.append("\nAverage: " + avg + "\nGrade: " + grade);
            }
        });

        // Add new subject marks
        addBtn.addActionListener(e -> {
            String roll = rollField.getText().trim();
            String subject = JOptionPane.showInputDialog(this, "Subject Name:");
            String markStr = JOptionPane.showInputDialog(this, "Marks (0-100):");

            if (roll.isEmpty() || subject == null || markStr == null) return;

            try {
                int marks = Integer.parseInt(markStr);
                Subject s = new Subject();
                s.setRollNo(roll);
                s.setSubjectName(subject);
                s.setMarks(marks);

                boolean success = service.addSubject(s);
                if (success) {
                    JOptionPane.showMessageDialog(this, "✅ Subject added.");
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Failed to add.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid number.");
            }
        });
    }

    private String getGrade(double avg) {
        if (avg >= 90) return "A";
        if (avg >= 80) return "B";
        if (avg >= 70) return "C";
        if (avg >= 60) return "D";
        if (avg >= 40) return "E";
        return "F";
    }
}
