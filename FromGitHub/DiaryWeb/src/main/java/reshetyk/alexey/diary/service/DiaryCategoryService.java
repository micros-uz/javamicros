/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reshetyk.alexey.diary.service;

import reshetyk.alexey.diary.domain.DiaryCategory;
import reshetyk.alexey.diary.domain.DiaryRecord;
import reshetyk.alexey.diary.domain.DiaryUser;

import java.util.List;

/**
 * @author Alexey
 */
public interface DiaryCategoryService {

    public void addCategory(DiaryCategory diaryCategory);

    public Integer saveOrUpdateCategory(DiaryCategory diaryCategory);

    public void updateCategory(DiaryCategory diaryCategory);

    public List<DiaryCategory> getListCategories();

    public List<DiaryCategory> getRootCategoriesByUser(DiaryUser user);

    public void removeCategory(Integer id);

    public List<DiaryCategory> getChildren(DiaryCategory diaryCategory);

    public DiaryCategory getCategoryById(Integer id);

    public List<DiaryRecord> getDiaryRecords(DiaryCategory diaryCategory);


}
