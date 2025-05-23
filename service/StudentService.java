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
    List<Student> searchStudents(String keyword); // 添加新的搜索方法
}