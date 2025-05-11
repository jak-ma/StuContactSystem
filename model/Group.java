package model;

public class Group {
    // 属性定义
    public int id;
    private String name;
    private String description;
    // 构造方法
    public Group() {

    }
    
    public Group(int id, String name,String description) {
        this.name = name;
        this.description = description;
    }
    // 编辑组群信息
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // 获取群组信息
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
