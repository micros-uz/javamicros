package reshetyk.alexey.diary.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reshetyk.alexey.diary.dao.DiaryUserDAO;
import reshetyk.alexey.diary.domain.DiaryUser;

import java.util.List;

@Transactional
@Repository
public class DiaryUserDAOImpl implements DiaryUserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(DiaryUser user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DiaryUser> getListUsers() {

        return sessionFactory.getCurrentSession().createCriteria(DiaryUser.class).list();
    }

    @Override
    public void removeUser(Integer id) {
        DiaryUser user = (DiaryUser) sessionFactory.getCurrentSession().load(DiaryUser.class, id);
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }

    }

    @Override
    public DiaryUser getUserById(Integer id) {
        return (DiaryUser) sessionFactory.getCurrentSession().get(DiaryUser.class, id);
    }

    @Override
    public DiaryUser getUserByLogin(String login) {
//        DiaryUser user = new DiaryUser(login); 
        try{
            Criteria c = sessionFactory.getCurrentSession().createCriteria(DiaryUser.class);
            c.add(Restrictions.eq("login", login));
            return (DiaryUser)c.uniqueResult(); 
        }
        catch(Exception e){

            return null;
            
        } 
    }
    
    
}
