package reshetyk.alexey.diary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reshetyk.alexey.diary.dao.DiaryUserDAO;
import reshetyk.alexey.diary.domain.DiaryUser;
import reshetyk.alexey.diary.service.DiaryUserService;

import java.util.List;
 
@Service
public class DiaryUserServiceImpl implements DiaryUserService{
 
    @Autowired
    private DiaryUserDAO userDAO;
 
    @Transactional
    @Override
    public void addUser(DiaryUser user) {
        userDAO.addUser(user);
    }
 
    @Transactional
    @Override
    public List<DiaryUser> getListUsers() {
 
        return userDAO.getListUsers();
    }
 
    @Transactional
    @Override
    public void removeUser(Integer id) {
        userDAO.removeUser(id);
    }
    
    @Transactional
    @Override
    public DiaryUser getUserById (Integer id) {
        return userDAO.getUserById (id);
    }

    @Override
    public DiaryUser getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }
}
