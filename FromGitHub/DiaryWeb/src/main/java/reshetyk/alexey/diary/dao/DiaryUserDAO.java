package reshetyk.alexey.diary.dao;

import reshetyk.alexey.diary.domain.DiaryUser;

import java.util.List;

public interface DiaryUserDAO {

    public void addUser(DiaryUser user);

    public List<DiaryUser> getListUsers();

    public void removeUser(Integer id);

    public DiaryUser getUserById(Integer id);

    public DiaryUser getUserByLogin(String login);
    
}