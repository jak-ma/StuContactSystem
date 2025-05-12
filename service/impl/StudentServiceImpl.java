package service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors; // 新增导入
import model.Student;
import dao.StudentDao;
import service.StudentService;
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = Objects.requireNonNull(studentDao,"StudentDao不能为空");
    }

    public void addStudent(Student student) {
        // 错误处理机制
        if (student.getName()==null || student.getName().trim().isEmpty()) {
            System.out.println("学生姓名不能为空");
            return;
        }
        if (studentDao.findStu(student.getName())!=null) {
            System.out.println("学生信息已经存在");
            return;
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

    @Override
    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllStudents(); // 如果关键词为空，返回所有学生
        }
        String lowerCaseKeyword = keyword.toLowerCase();
        return studentDao.findAllStu().stream()
                .filter(student -> (student.getName() != null && student.getName().toLowerCase().contains(lowerCaseKeyword)) ||
                                 (student.getPhone() != null && student.getPhone().toLowerCase().contains(lowerCaseKeyword)))
                .collect(Collectors.toList());
    }
}
