/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reshetyk.alexey.diary.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reshetyk.alexey.diary.dao.DiaryCategoryDAO;
import reshetyk.alexey.diary.domain.DiaryCategory;
import reshetyk.alexey.diary.domain.DiaryRecord;
import reshetyk.alexey.diary.domain.DiaryUser;

import java.util.List;

/**
 * @author Alexey
 */
@Transactional
@Repository
public class DiaryCategoryDAOImpl implements DiaryCategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addCategory(DiaryCategory diaryCategory) {
        sessionFactory.getCurrentSession().save(diaryCategory);
    }

    @Override
    public void updateCategory(DiaryCategory diaryCategory) {
        sessionFactory.getCurrentSession().update(diaryCategory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DiaryCategory> getListCategories() {
        return sessionFactory.getCurrentSession().createCriteria(DiaryCategory.class).list();
    }

    @Override
    public void removeCategory(Integer id) {
        DiaryCategory category = (DiaryCategory) sessionFactory.getCurrentSession().load(DiaryCategory.class, id);
        if (null != category) {
            sessionFactory.getCurrentSession().delete(category);
        }
    }

    @Override
    public List<DiaryCategory> getRootCategoriesByUser(DiaryUser user) {
//        System.out.println("----[getRootCategoriesByUser] user = " + user);
        return sessionFactory.getCurrentSession()
                .createCriteria(DiaryCategory.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.isNull("parent"))
                .addOrder(Order.asc("id"))
                .list();
    }

    @Override
    public List<DiaryCategory> getChildren(DiaryCategory diaryCategory) {
        return sessionFactory.getCurrentSession()
                .createCriteria(DiaryCategory.class)
                .add(Restrictions.eq("parent", diaryCategory))
                .addOrder(Order.asc("id"))
                .list();
    }

    @Override
    public DiaryCategory getCategoryById(Integer id) {
        return (DiaryCategory) sessionFactory.getCurrentSession().get(DiaryCategory.class, id);
    }

    @Override
    public List<DiaryRecord> getDiaryRecords(DiaryCategory diaryCategory) {
//        System.out.println("----[getDiaryRecords] diaryCategory = " + diaryCategory);
        return sessionFactory.getCurrentSession()
                .createCriteria(DiaryRecord.class)
                .add(Restrictions.eq("diaryCategory", diaryCategory))
                .addOrder(Order.asc("id"))
                .list();
    }

    @Override
    public Integer saveOrUpdateCategory(DiaryCategory diaryCategory) {
        sessionFactory.getCurrentSession().saveOrUpdate(diaryCategory);
        return diaryCategory.getId();
    }
}
