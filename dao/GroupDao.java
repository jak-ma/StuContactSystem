package dao;

import model.Group;
import java.util.List;
public interface GroupDao {
    // 添加组群
    void addGroup(Group group);
    // 删除组群
    void delGroup(String name);
    // 修改组群信息
    void updateGroup(Group group);
    // 查看组群
    Group findGroup(String name);
    // 查看所有组群
    List<Group> findAllGroup();
    // find
    Group findGroupById(int groupId);
}
