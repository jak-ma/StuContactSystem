package service.impl;

import java.util.List;
import java.util.Objects;
import model.Student;
import dao.StudentDao;
import service.StudentService;
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImpl() {
        this.studentDao=null;
    }
    
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = Objects.requireNonNull(studentDao,"StudentDao不能为空");
    }

    public void addStudent(Student student) {
        // 错误处理机制
        if (student.getName()==null || student.getName().trim().isEmpty()) {
            System.out.println("学生姓名不能为空");
        }
        if (studentDao.findStu(student.getName())!=null) {
            System.out.println("学生信息已经存在");
        }

        studentDao.addStu(student);
    }
    public void deleteStudentByName(String name) {
        // error

        studentDao.delStu(name);
    }

    public void updateStudent(Student student) {
        studentDao.updateStu(student);
    }

    public void findStudentByName(String name) {
        studentDao.findStu(name);
    }
    public List<Student> getAllStudents() {
        return studentDao.findAllStu();
    }

    public List<Student> getStudentsByGroupId(int groupId) {
        return studentDao.findGroupStu(groupId);
    }
}
