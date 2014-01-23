/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reshetyk.alexey.diary.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reshetyk.alexey.diary.dao.DiaryRecordDAO;
import reshetyk.alexey.diary.domain.DiaryRecord;

import java.util.List;

/**
 *
 * @author Alexey
 */
@Transactional
@Repository
public class DiaryRecordDAOImpl implements DiaryRecordDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRecord(DiaryRecord diaryRecord) {
        sessionFactory.getCurrentSession().save(diaryRecord);
    }

    @Override
    public void updateRecord(DiaryRecord diaryRecord) {
        sessionFactory.getCurrentSession().update(diaryRecord);
    }
    
    @Override
    public Integer saveOrUpdateRecord(DiaryRecord diaryRecord) {
        sessionFactory.getCurrentSession().saveOrUpdate(diaryRecord);
        return diaryRecord.getId();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DiaryRecord> getListRecords() {
        return sessionFactory.getCurrentSession().createCriteria(DiaryRecord.class).list();
    }

    @Override
    public void removeRecord(Integer id) {
        DiaryRecord record = (DiaryRecord) sessionFactory.getCurrentSession().load(DiaryRecord.class, id);
        if (null != record) {
            sessionFactory.getCurrentSession().delete(record);
        }
    }

    @Override
    public DiaryRecord getRecordById(Integer id) {
        return (DiaryRecord) sessionFactory.getCurrentSession().get(DiaryRecord.class, id);
    }
    
}
