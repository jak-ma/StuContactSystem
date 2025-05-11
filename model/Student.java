package model;

// 学生通讯信息类
public class Student {
    // 属性定义
    private int id;
    private String name;
    private String phone;
    private String email;
    private int groupId;
    // 构造方法
    public Student() {

    }

    public Student(int id, String name, String phone, String email, int groupId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.groupId = groupId;
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

    public void setStuGroupId(int groupId) {
        this.groupId = groupId;
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
 
    public int getGroupId() {
        return groupId;
    }
}

