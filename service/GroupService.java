package service;

import model.Group;
import java.util.List;

public interface GroupService {
    
    void addGroup(Group group);
    void deleteGroupByName(String name);
    void updateGroup(Group group);
    Group findGroupById(int groupId);
    Group findGroupByName(String name);
    List<Group> getAllGroups();
    
}
