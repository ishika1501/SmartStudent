package com.ishika.smartstudent.gui;

import com.ishika.smartstudent.model.Student;
import com.ishika.smartstudent.service.AdminService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchPanel extends JPanel {
    private final JTextArea result = new JTextArea(15, 70);
    private final AdminService service = new AdminService();

    public SearchPanel() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        JTextField q = new JTextField(20);
        JButton byDept  = new JButton("Dept");
        JButton byMarks = new JButton("Marks >");

        top.add(new JLabel("Query:"));
        top.add(q);
        top.add(byDept);
        top.add(byMarks);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(result), BorderLayout.CENTER);

        byDept.addActionListener(e -> print(service.searchByDept(q.getText())));
        byMarks.addActionListener(e -> {
            int min = Integer.parseInt(q.getText());
            print(service.searchByMarks(min, 100));
        });
    }

    private void print(List<Student> list) {
        result.setText("");
        list.forEach(s ->
                result.append(s.getId()+" "+s.getName()+" "+s.getDepartment()+" "+s.getMarks()+"\n"));
    }
}
