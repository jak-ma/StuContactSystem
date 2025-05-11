package service.impl;

import service.GroupService;
import model.Group;

import java.util.List;
import java.util.Objects;

import dao.GroupDao;
import dao.StudentDao;
public class GroupServiceImpl implements GroupService{
    // error
    private final GroupDao groupDao;
    public GroupServiceImpl() {
        this.groupDao=null;
    }

    public GroupServiceImpl(GroupDao groupDao, StudentDao studentDao) {
        this.groupDao = Objects.requireNonNull(groupDao, "GroupDao 不能为 null");
    }

    public void addGroup(Group group) {
        groupDao.addGroup(group);
    }

    public void deleteGroupByName(String name) {
        groupDao.delGroup(name);
    }

    public void updateGroup(Group group) {
        groupDao.updateGroup(group);
    }

    public Group findGroupByName(String name) {
        return groupDao.findGroup(name);
    }
    
    public List<Group> getAllGroups() {
        return groupDao.findAllGroup();
    }

    public Group findGroupById(int GroupId) {
        return groupDao.findGroupById(GroupId);
    }
}
