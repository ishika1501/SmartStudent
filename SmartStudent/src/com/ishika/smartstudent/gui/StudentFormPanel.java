package com.ishika.smartstudent.gui;

import com.ishika.smartstudent.model.Student;
import com.ishika.smartstudent.service.AdminService;

import javax.swing.*;

public class StudentFormPanel extends JPanel {
    public StudentFormPanel() {
        setLayout(null);

        JLabel nameL = new JLabel("Name:");
        JLabel rollL = new JLabel("Roll No:");
        JLabel deptL = new JLabel("Department:");
        JLabel emailL = new JLabel("Email:");
        JLabel phoneL = new JLabel("Phone:");
        JLabel marksL = new JLabel("Marks:");

        JTextField nameF = new JTextField();
        JTextField rollF = new JTextField();
        JTextField deptF = new JTextField();
        JTextField emailF = new JTextField();
        JTextField phoneF = new JTextField();
        JTextField marksF = new JTextField();

        JButton addBtn = new JButton("Add Student");

        nameL.setBounds(30, 20, 100, 25); nameF.setBounds(150, 20, 200, 25);
        rollL.setBounds(30, 60, 100, 25); rollF.setBounds(150, 60, 200, 25);
        deptL.setBounds(30, 100, 100, 25); deptF.setBounds(150, 100, 200, 25);
        emailL.setBounds(30, 140, 100, 25); emailF.setBounds(150, 140, 200, 25);
        phoneL.setBounds(30, 180, 100, 25); phoneF.setBounds(150, 180, 200, 25);
        marksL.setBounds(30, 220, 100, 25); marksF.setBounds(150, 220, 200, 25);
        addBtn.setBounds(150, 270, 150, 30);

        add(nameL); add(nameF); add(rollL); add(rollF); add(deptL); add(deptF);
        add(emailL); add(emailF); add(phoneL); add(phoneF); add(marksL); add(marksF);
        add(addBtn);

        addBtn.addActionListener(e -> {
            Student s = new Student();
            s.setName(nameF.getText());
            s.setRollNo(rollF.getText());
            s.setDepartment(deptF.getText());
            s.setEmail(emailF.getText());
            s.setPhone(phoneF.getText());
            s.setMarks(Integer.parseInt(marksF.getText()));

            AdminService service = new AdminService();
            if (service.addStudent(s)) {
                JOptionPane.showMessageDialog(this, "✅ Student Added");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to add student");
            }
        });
    }
}
