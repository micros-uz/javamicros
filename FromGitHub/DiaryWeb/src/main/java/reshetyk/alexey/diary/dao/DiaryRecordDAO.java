/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reshetyk.alexey.diary.dao;

import reshetyk.alexey.diary.domain.DiaryRecord;

import java.util.List;

/**
 *
 * @author Alexey
 */
public interface DiaryRecordDAO {

    public void addRecord(DiaryRecord diaryRecord);
    
    public void updateRecord(DiaryRecord diaryRecord);
    
    public Integer saveOrUpdateRecord(DiaryRecord diaryRecord);

    public List<DiaryRecord> getListRecords();

    public void removeRecord(Integer id);
    
    public DiaryRecord getRecordById (Integer id);
}
