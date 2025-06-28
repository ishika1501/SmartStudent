package com.ishika.smartstudent.util;

import com.ishika.smartstudent.model.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter {
    public static void export(List<Student> students, String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write("ID,Name,Roll No,Department,Email,Phone,Marks\n");

        for (Student s : students) {
            writer.write(String.format("%d,%s,%s,%s,%s,%s,%d\n",
                    s.getId(),
                    s.getName(),
                    s.getRollNo(),
                    s.getDepartment(),
                    s.getEmail(),
                    s.getPhone(),
                    s.getMarks()
            ));
        }

        writer.close();
    }
}
