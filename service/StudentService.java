package service;

import model.Student;
import java.util.List;

public interface StudentService {

    void addStudent(Student student);
    void deleteStudentByName(String name);
    void updateStudent(Student student);
    void findStudentByName(String name);
    List<Student> getAllStudents();
    List<Student> getStudentsByGroupId(int groupId);
    
}