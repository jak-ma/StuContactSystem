package model;

import java.util.ArrayList;
import java.util.List;
// 学生通讯信息类
public class Student {
    // 属性定义
    private int id;
    private String name;
    private String phone;
    private String email;
    private List<Integer> groupIds;
    // 构造方法
    public Student(int id, String name, String phone, String email, List<Integer> groupIds) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.groupIds = new ArrayList<>(groupIds);
    }
    // 编辑学生信息
    public void setId(int id) {
        this.id = id;
    }

    public void setStuName(String name) {
        this.name = name;
    }

    public void setStuPhone(String phone) {
        this.phone = phone;
    }

    public void setStuEmail(String email) {
        this.email = email;
    }

    public void setStuGroupIds(List<Integer> groupIds) {
        this.groupIds = new ArrayList<>(groupIds);
    }

    // 获取学生信息
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
 
    public List<Integer> getGroupIds() {
        return new ArrayList<>(groupIds);
    }
}

