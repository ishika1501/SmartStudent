package com.ishika.smartstudent.service;

import com.ishika.smartstudent.dao.StudentDAO;
import com.ishika.smartstudent.model.Student;

import java.util.List;

public class AdminService {
    private final StudentDAO dao = new StudentDAO();

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    public boolean login(String u, String p) {
        return USERNAME.equals(u) && PASSWORD.equals(p);
    }

    public boolean addStudent(Student s) { return dao.addStudent(s); }
    public List<Student> viewAllStudents() { return dao.getAll(); }
    public Student findByRoll(String roll) { return dao.getByRoll(roll); }
    public boolean updateStudent(Student s) { return dao.update(s); }
    public boolean deleteByRoll(String roll) { return dao.delete(roll); }
    public List<Student> searchByDept(String dept) { return dao.searchByDept(dept); }
    public List<Student> searchByMarks(int min, int max) { return dao.searchByMarksRange(min, max); }
    public int totalStudents() { return dao.totalStudents(); }
    public int highestMarks() { return dao.highestMarks(); }
    public int lowestMarks() { return dao.lowestMarks(); }
}
