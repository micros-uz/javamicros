/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reshetyk.alexey.diary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reshetyk.alexey.diary.domain.DiaryUser;
import reshetyk.alexey.diary.service.DiaryUserService;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 *
 * @author Alexey
 */
@Controller
public class RegistrationController {

    @Autowired
    private DiaryUserService userService;

    @RequestMapping(value = "/registrationForm", method = RequestMethod.GET)
    public String registration(Map<String, Object> mapDataForView) {
        mapDataForView.put("user", new DiaryUser());
        return "registration";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") DiaryUser diaryUser,
            BindingResult result, HttpSession session) {
        
        userService.addUser(diaryUser);
        
        return "redirect:/";
    }
}
