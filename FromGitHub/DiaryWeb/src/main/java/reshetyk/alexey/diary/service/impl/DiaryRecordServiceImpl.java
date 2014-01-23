package reshetyk.alexey.diary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reshetyk.alexey.diary.dao.DiaryRecordDAO;
import reshetyk.alexey.diary.domain.DiaryRecord;
import reshetyk.alexey.diary.service.DiaryRecordService;

import java.util.List;
 
@Service
public class DiaryRecordServiceImpl implements DiaryRecordService{
 
    @Autowired
    private DiaryRecordDAO diaryRecordDAO;

    @Transactional
    @Override
    public void addRecord(DiaryRecord dAO) {
        diaryRecordDAO.addRecord(dAO);
    }

    @Transactional
    @Override
    public List<DiaryRecord> getListRecords() {
        return diaryRecordDAO.getListRecords();
    }

    @Transactional
    @Override
    public void removeRecord(Integer id) {
        diaryRecordDAO.removeRecord(id);
    }

    @Override
    public DiaryRecord getRecordById(Integer id) {
        return diaryRecordDAO.getRecordById(id);
    }

    @Override
    public void updateRecord(DiaryRecord diaryRecord) {
        diaryRecordDAO.updateRecord(diaryRecord);
    }

    @Override
    public Integer saveOrUpdateRecord(DiaryRecord diaryRecord) {
       return diaryRecordDAO.saveOrUpdateRecord(diaryRecord);
    }
}
