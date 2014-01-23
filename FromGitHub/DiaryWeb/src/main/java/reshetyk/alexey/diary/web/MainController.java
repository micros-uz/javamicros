package reshetyk.alexey.diary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UrlPathHelper;
import reshetyk.alexey.diary.domain.DiaryCategory;
import reshetyk.alexey.diary.domain.DiaryRecord;
import reshetyk.alexey.diary.domain.DiaryUser;
import reshetyk.alexey.diary.service.DiaryCategoryService;
import reshetyk.alexey.diary.service.DiaryUserService;
import reshetyk.alexey.diary.utils.DiarySession;
import reshetyk.alexey.diary.utils.RendererTreeCategories;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    public static final String MAIN_FORM = "main";

    @Autowired
    private DiaryUserService userService;
    @Autowired
    private DiaryCategoryService diaryCategoryService;

    @RequestMapping("/")
    public String listCategories(Map<String, Object> map, HttpSession session, HttpServletRequest httpServletRequest) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();

        DiarySession.setActiveUser(getActiveUser(), session);
        DiarySession.setContextPath(urlPathHelper.getContextPath(httpServletRequest), session);

        prepareDataForView(map, session);

        return MAIN_FORM;
    }

    @RequestMapping(value = "/diaryCategory/id/{diaryCategoryId}", method = RequestMethod.GET)
    public String selectDiaryCategory(@PathVariable("diaryCategoryId") Integer diaryCategoryId,
                                      Map<String, Object> map, HttpSession session) {

        DiarySession.setActiveCategory(diaryCategoryService.getCategoryById(diaryCategoryId), session);

        prepareDataForView(map, session);

        return MAIN_FORM;
    }

    private void prepareDataForView(Map mapDataForView, HttpSession session) {

        DiaryUser activeUser = DiarySession.getActiveUser(session);

        List<DiaryRecord> diaryRecords = new ArrayList<DiaryRecord>();

        DiaryCategory activeCategory = new DiaryCategory();
        DiaryRecord activeRecord = new DiaryRecord();


        if (DiarySession.getActiveCategory(session) != null) {
            activeCategory = DiarySession.getActiveCategory(session);
            if (activeCategory.getId() != null) {
                diaryRecords = diaryCategoryService.getDiaryRecords(activeCategory);
            }
        }
        activeCategory.setUser(activeUser);

        if (DiarySession.getActiveRecord(session) != null) {
            activeRecord = DiarySession.getActiveRecord(session);
        }

        List<DiaryCategory> diaryRootCategories = diaryCategoryService.getRootCategoriesByUser(activeUser);

        RendererTreeCategories rendererTreeCategories = new RendererTreeCategories(diaryCategoryService);

        mapDataForView.put("user", activeUser);
        mapDataForView.put("categoriesTreeRendered", rendererTreeCategories.toHtmlList(diaryRootCategories, DiarySession.getContextPath(session)));
        mapDataForView.put("records", diaryRecords);
        mapDataForView.put("diaryRecordForm", activeRecord);
    }

    private DiaryUser getActiveUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
//        if (username == null) redirect /login

        return userService.getUserByLogin(username);
    }

}
