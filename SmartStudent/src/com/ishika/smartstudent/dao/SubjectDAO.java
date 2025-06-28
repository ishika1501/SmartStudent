package com.ishika.smartstudent.dao;

import com.ishika.smartstudent.db.DatabaseConnection;
import com.ishika.smartstudent.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    public List<Subject> getSubjectsByRollNo(String rollNo) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subjects WHERE roll_no = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rollNo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getInt("id"));
                s.setRollNo(rs.getString("roll_no"));
                s.setSubjectName(rs.getString("subject_name"));
                s.setMarks(rs.getInt("marks"));
                list.add(s);
            }

        } catch (SQLException e) {
            System.err.println("❌ getSubjectsByRollNo: " + e.getMessage());
        }

        return list;
    }

    public boolean addSubject(Subject s) {
        String sql = "INSERT INTO subjects (roll_no, subject_name, marks) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getRollNo());
            stmt.setString(2, s.getSubjectName());
            stmt.setInt(3, s.getMarks());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ addSubject: " + e.getMessage());
        }

        return false;
    }
}
