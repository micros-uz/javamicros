/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reshetyk.alexey.diary.service;

import reshetyk.alexey.diary.domain.DiaryRecord;

import java.util.List;

/**
 *
 * @author Alexey
 */
public interface DiaryRecordService {

    public void addRecord(DiaryRecord diaryRecord);
    
    public void updateRecord(DiaryRecord diaryRecord);
    
    public Integer saveOrUpdateRecord(DiaryRecord diaryRecord);

    public List<DiaryRecord> getListRecords();

    public void removeRecord(Integer id);
    
    public DiaryRecord getRecordById(Integer id);
}
