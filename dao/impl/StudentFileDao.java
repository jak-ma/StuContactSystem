package dao.impl;

import dao.StudentDao;
import model.Student;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

// 记录:
// 这个程序存在并发问题，并没有时间解决目前

public class StudentFileDao implements StudentDao {
    // 有时间加入->文件自动迁移功能
    private static final String FILE_PATH = "dao/data/students.json";
    // ID自增
    private int curMaxId = 0;
    // 加载学生信息
    private List<Student> loadStudentsFromFile() {
        List<Student> students = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            if (content.isEmpty()) {
                return students; // 文件为空，返回空列表
            }
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Student student = new Student();
                student.setId(jsonObject.getInt("id"));
                student.setName(jsonObject.getString("name"));
                student.setPhone(jsonObject.getString("phone"));
                student.setEmail(jsonObject.getString("email"));
                student.setGroupId(jsonObject.getInt("groupId"));

                students.add(student);
            }
        } catch (IOException e) {
            // 文件不存在或无法读取时，可以认为是空的，或者打印错误日志
            System.err.println("Error loading students from file: " + e.getMessage());
            // 返回空列表，或者根据需求抛出自定义异常
        } catch (org.json.JSONException e) {
            // JSON解析错误
            System.err.println("Error parsing students JSON: " + e.getMessage());
        }
        if (!students.isEmpty()) {
            // findGroupStu() 已经使用过 stream()
            // Student::getId 是方法引用 等价于 student->student.getId()
            this.curMaxId = students.stream().mapToInt(Student::getId).max().orElse(0);
        } else {
            this.curMaxId = 0;
        }
        return students;
    }
    // 保存学生信息
    private void saveStudentsToFile(List<Student> students) {
        JSONArray jsonArray = new JSONArray();
        for (Student student : students) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", student.getId());
            jsonObject.put("name", student.getName());
            jsonObject.put("phone", student.getPhone());
            jsonObject.put("email", student.getEmail());
            // JSONObject.put 会自动处理 List<Integer> 转成 JSONArray
            // 上面是一开始的逻辑，后面进行了修改，一个学生只属于一个组
            jsonObject.put("groupId", student.getGroupId());
            jsonArray.put(jsonObject);
        }
        try {
            // 同样注意 FILE_PATH 的相对路径问题
            Files.write(Paths.get(FILE_PATH), jsonArray.toString(4).getBytes()); 
        } catch (IOException e) {
            System.err.println("Error saving students to file: " + e.getMessage());
            // 根据需求抛出自定义异常
        } catch (org.json.JSONException e) {
            // 一般在 put 时不太会发生，除非 key 为 null
            System.err.println("Error creating students JSON: " + e.getMessage());
        }
    }
    // 实现接口
    public void addStu(Student stu) {
        List<Student> students = loadStudentsFromFile();
        // 唯一性检查-暂时先不写逻辑-后面有时间再写
        System.out.println("Before add, student count: " + students.size());
        // 自增 Id 生成
        this.curMaxId++;
        stu.setId(curMaxId);

        students.add(stu);
        saveStudentsToFile(students);
        System.out.println("After add, student count: " + students.size());
    }

    public void delStu(String name) {
        List<Student> students = loadStudentsFromFile();
        students.removeIf(student->student.getName().equals(name));
        saveStudentsToFile(students);
    }

    public void updateStu(Student updatedStu) {
        List<Student> students = loadStudentsFromFile();
        for (int i = 0;i < students.size();i++) {
            if (students.get(i).getName().equals(updatedStu.getName())) {
                students.set(i, updatedStu);
                break;
            }
        }
        saveStudentsToFile(students);
    }
    
    public Student findStu(String name) {
        List<Student> students = loadStudentsFromFile();
        for (Student student:students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> findAllStu() {
        return loadStudentsFromFile();
    }
    // 经过查询和ai辅助，有点复杂的语法
    public List<Student> findGroupStu(int groupId) {
        List<Student> students = loadStudentsFromFile();
        return students.stream().filter(student->student.getGroupId()==groupId).collect(Collectors.toList());
        // 笔记:
        // students.stream() 将传入的学生集合转换成一个流对象
        // student->student.getGroupId()==groupId 相当于一个过滤器
        // collect(Collectors.toList()) 被保留下来的学生对象重新组成一个学生列表对象
    }
}
