package com.ishika.smartstudent.dao;

import com.ishika.smartstudent.db.DatabaseConnection;
import com.ishika.smartstudent.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data-access layer for Student entities (CRUD + search + stats).
 */
public class StudentDAO {

    /* ========== C R U D  ========== */

    public boolean addStudent(Student s) {
        final String sql = """
                INSERT INTO students (name, roll_no, department, email, phone, marks)
                VALUES (?,?,?,?,?,?)""";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setString(2, s.getRollNo());
            ps.setString(3, s.getDepartment());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getPhone());
            ps.setInt   (6, s.getMarks());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.err.println("❌ addStudent: " + ex.getMessage());
            return false;
        }
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st  = con.createStatement();
             ResultSet rs  = st.executeQuery(sql)) {

            while (rs.next()) list.add(map(rs));
        } catch (SQLException ex) {
            System.err.println("❌ getAll: " + ex.getMessage());
        }
        return list;
    }

    public Student getByRoll(String roll) {
        String sql = "SELECT * FROM students WHERE roll_no = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, roll);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
        } catch (SQLException ex) {
            System.err.println("❌ getByRoll: " + ex.getMessage());
        }
        return null;
    }

    public boolean update(Student s) {
        final String sql = """
                UPDATE students
                   SET name = ?, department = ?, email = ?, phone = ?, marks = ?
                 WHERE roll_no = ?""";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getPhone());
            ps.setInt   (5, s.getMarks());
            ps.setString(6, s.getRollNo());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.err.println("❌ update: " + ex.getMessage());
            return false;
        }
    }

    public boolean delete(String roll) {
        String sql = "DELETE FROM students WHERE roll_no = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, roll);
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.err.println("❌ delete: " + ex.getMessage());
            return false;
        }
    }


    /* ========== SEARCH & FILTER  ========== */

    public List<Student> searchByDept(String dept) {
        return queryList("SELECT * FROM students WHERE department = ?", dept);
    }

    public List<Student> searchByMarksRange(int min, int max) {
        return queryList("SELECT * FROM students WHERE marks BETWEEN ? AND ?", min, max);
    }


    /* ========== STATISTICS (Optional)  ========== */

    public int totalStudents()  { return scalarInt("SELECT COUNT(*) FROM students"); }
    public int highestMarks()   { return scalarInt("SELECT MAX(marks) FROM students"); }
    public int lowestMarks()    { return scalarInt("SELECT MIN(marks) FROM students"); }


    /* ========== Helper Methods  ========== */

    /** Maps current ResultSet row to a Student object */
    private Student map(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setId        (rs.getInt   ("id"));
        s.setName      (rs.getString("name"));
        s.setRollNo    (rs.getString("roll_no"));
        s.setDepartment(rs.getString("department"));
        s.setEmail     (rs.getString("email"));
        s.setPhone     (rs.getString("phone"));
        s.setMarks     (rs.getInt   ("marks"));
        return s;
    }

    /** Generic “list” query helper */
    private List<Student> queryList(String sql, Object... params) {
        List<Student> list = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) ps.setObject(i + 1, params[i]);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));

        } catch (SQLException ex) {
            System.err.println("❌ queryList: " + ex.getMessage());
        }
        return list;
    }

    /** Executes a query that returns a single int (e.g., COUNT, MAX, MIN) */
    private int scalarInt(String sql) {
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException ex) {
            System.err.println("❌ scalarInt: " + ex.getMessage());
            return 0;
        }
    }
}
