package dao.impl;

import dao.GroupDao;
import model.Group;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GroupFileDao implements GroupDao {
    private static final String FILE_PATH = "../data/groups.json";
    // ID自增
    private int curMaxId = 0;
    // 加载分组信息
    private List<Group> loadGroupsFromFile() {
        List<Group> groups = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            if (content.isEmpty()) {
                return groups;
            }
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Group group = new Group();
                group.setId(jsonObject.getInt("id"));
                group.setName(jsonObject.getString("name"));
                group.setDescription(jsonObject.getString("description"));

                groups.add(group);
            }
        } catch (IOException e) {
            // 文件不存在或无法读取时，可以认为是空的，或者打印错误日志
            System.err.println("Error loading students from file: " + e.getMessage());
            // 返回空列表，或者根据需求抛出自定义异常
        } catch (org.json.JSONException e) {
            // JSON解析错误
            System.err.println("Error parsing students JSON: " + e.getMessage());
        }
        if (!groups.isEmpty()) {
            this.curMaxId = groups.stream().mapToInt(Group::getId).max().orElse(0);
        } else {
            this.curMaxId = 0;
        }
        return groups;
    }
    // 保存分组信息、
    private void saveGroupsToFile(List<Group> groups) {
        JSONArray jsonArray = new JSONArray();
        for (Group group : groups) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", group.getId());
            jsonObject.put("name", group.getName());
            jsonObject.put("description", group.getDescription());

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
    public void addGroup(Group group) {
        List<Group> groups = loadGroupsFromFile();

        this.curMaxId++;
        group.setId(curMaxId);

        groups.add(group);
        saveGroupsToFile(groups);
    }
    public void delGroup(String name) {
        List<Group> groups = loadGroupsFromFile();
        groups.removeIf(group->group.getName().equals(name));
        saveGroupsToFile(groups);
    }
    public void updateGroup(Group updategroup) {
        List<Group> groups = loadGroupsFromFile();
        for (int i = 0;i < groups.size();i++) {
            // 改用equals()来比较而不是用等号
            if (groups.get(i).getName().equals(updategroup.getName())) {
                groups.set(i, updategroup);
                break;
            }
        }
        saveGroupsToFile(groups);
    }
    public Group findGroup(String name) {
        List<Group> groups = loadGroupsFromFile();
        for (Group group:groups) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }
    // new_add_func
    public Group findGroupById(int groupId) {
        List<Group> groups = loadGroupsFromFile();
        for (Group group:groups) {
            if (group.getId()==groupId) {
                return group;
            }
        }
        return null;
    }

    public List<Group> findAllGroup() {
        return loadGroupsFromFile();
    }
}
