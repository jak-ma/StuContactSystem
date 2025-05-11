package dao;

import java.util.List;
import model.Student; 
public interface StudentDao {
    // 添加学生
    void addStu(Student stu);
    // 删除学生
    void delStu(String name);
    // 修改信息
    void updateStu(Student stu);
    // 查看学生
    Student findStu(String name);
    // 查看所有学生
    List<Student> findAllStu();
    // 查看某个组群学生
    List<Student> findGroupStu(int groupId);
}
