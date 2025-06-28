package com.ishika.smartstudent.model;

public class Subject {
    private int id;
    private String rollNo;
    private String subjectName;
    private int marks;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }
}
