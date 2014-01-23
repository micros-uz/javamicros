package reshetyk.alexey.diary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reshetyk.alexey.diary.dao.DiaryCategoryDAO;
import reshetyk.alexey.diary.domain.DiaryCategory;
import reshetyk.alexey.diary.domain.DiaryRecord;
import reshetyk.alexey.diary.domain.DiaryUser;
import reshetyk.alexey.diary.service.DiaryCategoryService;

import java.util.List;

@Service
public class DiaryCategoryServiceImpl implements DiaryCategoryService {

    @Autowired
    private DiaryCategoryDAO diaryCategoryDAO;

    @Transactional
    @Override
    public void addCategory(DiaryCategory diaryCategory) {
        diaryCategoryDAO.addCategory(diaryCategory);
    }

    @Transactional
    @Override
    public List<DiaryCategory> getListCategories() {
        return diaryCategoryDAO.getListCategories();
    }

    @Transactional
    @Override
    public List<DiaryCategory> getRootCategoriesByUser(DiaryUser user) {
        return diaryCategoryDAO.getRootCategoriesByUser(user);
    }


    @Transactional
    @Override
    public void removeCategory(Integer id) {
        diaryCategoryDAO.removeCategory(id);
    }

    @Transactional
    @Override
    public List<DiaryCategory> getChildren(DiaryCategory diaryCategory) {
        return diaryCategoryDAO.getChildren(diaryCategory);
    }

    @Override
    public DiaryCategory getCategoryById(Integer id) {
        return diaryCategoryDAO.getCategoryById(id);
    }

    @Override
    public List<DiaryRecord> getDiaryRecords(DiaryCategory diaryCategory) {
        return diaryCategoryDAO.getDiaryRecords(diaryCategory);
    }

    @Override
    public Integer saveOrUpdateCategory(DiaryCategory diaryCategory) {
        return diaryCategoryDAO.saveOrUpdateCategory(diaryCategory);
    }

    @Override
    public void updateCategory(DiaryCategory diaryCategory) {
        diaryCategoryDAO.updateCategory(diaryCategory);
    }
}
