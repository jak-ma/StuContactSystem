package dao;

import model.Group;
import java.util.List;
public interface GroupDao {
    // 添加组群
    void addGroup(Group group);
    // 删除组群
    void delGroup(int groupId);
    // 修改组群信息
    void updateGroup(Group group);
    // 查看组群
    Group findGroup(int groupId);
    // 查看所有组群
    List<Group> findAllGroup();
}
