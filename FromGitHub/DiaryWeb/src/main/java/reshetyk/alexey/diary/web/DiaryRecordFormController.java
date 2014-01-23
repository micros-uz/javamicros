package reshetyk.alexey.diary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reshetyk.alexey.diary.domain.DiaryCategory;
import reshetyk.alexey.diary.domain.DiaryRecord;
import reshetyk.alexey.diary.service.DiaryRecordService;
import reshetyk.alexey.diary.utils.DiarySession;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class DiaryRecordFormController {

    public static final String DIARY_RECORD_FORM = "diaryRecordForm";

    @Autowired
    private DiaryRecordService diaryRecordService;

    @RequestMapping(value = "/diaryRecord/showFormEdit/id/{diaryRecordId}", method = RequestMethod.GET)
    public String showFormEdit(@PathVariable("diaryRecordId") Integer diaryRecordId,
                               Map<String, Object> mapDataForView, HttpSession session) {

        DiaryRecord activeRecord = diaryRecordService.getRecordById(diaryRecordId);

        DiarySession.setActiveRecord(activeRecord, session);

        mapDataForView.put(DIARY_RECORD_FORM, activeRecord);

        return DIARY_RECORD_FORM;
    }

    @RequestMapping(value = "/diaryRecord/showFormAdd", method = RequestMethod.GET)
    public String showFormAdd(Map<String, Object> mapDataForView, HttpSession session) {

        DiaryRecord diaryRecord = new DiaryRecord(DiarySession.getActiveCategory(session));

        DiarySession.setActiveRecord(diaryRecord, session);

        mapDataForView.put(DIARY_RECORD_FORM, diaryRecord);

        return DIARY_RECORD_FORM;
    }

    @RequestMapping(value = "/saveOrUpdateRecord", method = RequestMethod.POST)
    public String saveOrUpdateRecord(@ModelAttribute("diaryRecord") DiaryRecord diaryRecordForm,
                                     BindingResult result, HttpSession session) {

        DiaryRecord diaryRecord = (DiaryRecord) session.getAttribute("activeRecord");
        diaryRecord.setName(diaryRecordForm.getName());
        diaryRecord.setText(diaryRecordForm.getText());

        //TODO: Logging!
        diaryRecordService.saveOrUpdateRecord(diaryRecord);

        return "redirect:" + getPathActiveCategory(session);
    }

    @RequestMapping(value = "/diaryRecord/delete/id/{diaryRecordId}", method = RequestMethod.GET)
    public String deleteRecord(@PathVariable("diaryRecordId") Integer diaryRecordId,
                               Map<String, Object> map, HttpSession session) {

        //TODO: Logging!
        diaryRecordService.removeRecord(diaryRecordId);

        return "redirect:" + getPathActiveCategory(session);
    }

    private String getPathActiveCategory(HttpSession session) {
        DiaryCategory diaryCategory = (DiaryCategory) session.getAttribute("activeCategory");

        return "/diaryCategory/id/" + diaryCategory.getId();

    }

}
