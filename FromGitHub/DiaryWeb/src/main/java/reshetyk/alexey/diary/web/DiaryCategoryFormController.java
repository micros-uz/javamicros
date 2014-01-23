package reshetyk.alexey.diary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reshetyk.alexey.diary.domain.DiaryCategory;
import reshetyk.alexey.diary.domain.DiaryUser;
import reshetyk.alexey.diary.service.DiaryCategoryService;
import reshetyk.alexey.diary.utils.DiarySession;
import reshetyk.alexey.diary.utils.RendererTreeCategories;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class DiaryCategoryFormController {

    public static final String DIARY_CATEGORY_FORM = "diaryCategoryForm";

    @Autowired
    private DiaryCategoryService diaryCategoryService;


    @RequestMapping(value = "/diaryCategory/showFormAdd/root", method = RequestMethod.GET)
    public String showFormAddRoot(Map<String, Object> mapDataForView, HttpSession session) {

        prepareDataForShowForm(mapDataForView, session, false);

        return DIARY_CATEGORY_FORM;
    }


    @RequestMapping(value = "/diaryCategory/showFormAdd/child", method = RequestMethod.GET)
    public String showFormAddChild(Map<String, Object> mapDataForView, HttpSession session) {

        prepareDataForShowForm(mapDataForView, session, true);

        return DIARY_CATEGORY_FORM;
    }

    @RequestMapping(value = "/diaryCategory/showFormEdit", method = RequestMethod.GET)
    public String showFormEdit(Map<String, Object> mapDataForView, HttpSession session) {

        DiaryCategory activeCategory = DiarySession.getActiveCategory(session);

        prepareMapDataForView(mapDataForView, activeCategory, session, "update");

        return DIARY_CATEGORY_FORM;
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("diaryCategory") DiaryCategory diaryCategoryForm,
                              BindingResult result, HttpSession session) {

        DiaryUser activeUser = DiarySession.getActiveUser(session);

        DiaryCategory diaryCategoryParent = null;
        if (diaryCategoryForm.getParent().getId() != null) {
            diaryCategoryParent = diaryCategoryService.getCategoryById(diaryCategoryForm.getParent().getId());
        }
        diaryCategoryForm.setParent(diaryCategoryParent);

        diaryCategoryForm.setUser(activeUser);

        Integer idCateg = diaryCategoryService.saveOrUpdateCategory(diaryCategoryForm);

        return "redirect:/diaryCategory/id/" + idCateg;

    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    public String updateCategory(@ModelAttribute("diaryCategory") DiaryCategory diaryCategoryForm,
                                 BindingResult result, HttpSession session) {

        DiaryCategory activeCategory = DiarySession.getActiveCategory(session);

        DiaryUser activeUser = DiarySession.getActiveUser(session);

        DiaryCategory diaryCategoryParent = null;
        if (diaryCategoryForm.getParent().getId() != null) {
            diaryCategoryParent = diaryCategoryService.getCategoryById(diaryCategoryForm.getParent().getId());
        }
        diaryCategoryForm.setId(activeCategory.getId());

        diaryCategoryForm.setParent(diaryCategoryParent);

        diaryCategoryForm.setUser(activeUser);

        Integer idCateg = diaryCategoryService.saveOrUpdateCategory(diaryCategoryForm);

        return "redirect:/diaryCategory/id/" + idCateg;

    }

    public void prepareDataForShowForm(Map<String, Object> mapDataForView, HttpSession session, boolean isChild) {

        DiaryUser activeUser = DiarySession.getActiveUser(session);
        DiaryCategory parentCategory = null;

        if (isChild) {
            parentCategory = DiarySession.getActiveCategory(session);
        }

        DiaryCategory categoryForm = new DiaryCategory();
        categoryForm.setUser(activeUser);
        categoryForm.setParent(parentCategory);

        prepareMapDataForView(mapDataForView, categoryForm, session, "add");
    }

    private String getRenderedSelListCateg(DiaryCategory activeCategory, HttpSession session) {

        DiaryUser activeUser = DiarySession.getActiveUser(session);

        List<DiaryCategory> diaryRootCategories = diaryCategoryService.getRootCategoriesByUser(activeUser);
        RendererTreeCategories rendererTreeCategories = new RendererTreeCategories(diaryCategoryService);

        return rendererTreeCategories.toHtmlSelectList(diaryRootCategories, activeCategory);
    }

    private void prepareMapDataForView(Map<String, Object> mapDataForView, DiaryCategory activeCategory, HttpSession session, String action) {
        String categoriesTreeRenderedToSelectList = getRenderedSelListCateg(activeCategory, session);

        mapDataForView.put("action", action);
        mapDataForView.put("categoriesTreeRenderedToSelectList", categoriesTreeRenderedToSelectList);
        mapDataForView.put(DIARY_CATEGORY_FORM, activeCategory);

    }

}
