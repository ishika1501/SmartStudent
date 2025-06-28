package com.ishika.smartstudent.service;

import com.ishika.smartstudent.dao.SubjectDAO;
import com.ishika.smartstudent.model.Subject;

import java.util.List;

public class SubjectService {
    private final SubjectDAO dao = new SubjectDAO();

    public List<Subject> getSubjectsByRollNo(String rollNo) {
        return dao.getSubjectsByRollNo(rollNo);
    }

    public boolean addSubject(Subject s) {
        return dao.addSubject(s);
    }
}
