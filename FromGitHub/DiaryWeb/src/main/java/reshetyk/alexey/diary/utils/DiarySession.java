/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reshetyk.alexey.diary.utils;

import reshetyk.alexey.diary.domain.DiaryCategory;
import reshetyk.alexey.diary.domain.DiaryRecord;
import reshetyk.alexey.diary.domain.DiaryUser;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexey
 */
public class DiarySession {

    private static final String ATTR_NAME_ACTIVE_USER = "activeUser";
    private static final String ATTR_NAME_ACTIVE_CATEGORY = "activeCategory";
    private static final String ATTR_NAME_ACTIVE_RECORD = "activeRecord";
    private static final String ATTR_NAME_CONTEXT_PATH = "contextPath";
    
    
    public static DiaryUser getActiveUser (HttpSession session) {
        
        return (DiaryUser) session.getAttribute(ATTR_NAME_ACTIVE_USER);
    }
    
    public static void setActiveUser (DiaryUser diaryUser, HttpSession session) {
        
        session.setAttribute(ATTR_NAME_ACTIVE_USER, diaryUser);
    }
    
    public static DiaryCategory getActiveCategory (HttpSession session) {
        
        return (DiaryCategory) session.getAttribute(ATTR_NAME_ACTIVE_CATEGORY);
    }
    
    public static void setActiveCategory (DiaryCategory diaryCategory, HttpSession session) {
        
        session.setAttribute(ATTR_NAME_ACTIVE_CATEGORY, diaryCategory);
    }
    
    public static DiaryRecord getActiveRecord (HttpSession session) {
        
        return (DiaryRecord) session.getAttribute(ATTR_NAME_ACTIVE_RECORD);
    }
    
    public static void setActiveRecord (DiaryRecord diaryRecord, HttpSession session) {
        
        session.setAttribute(ATTR_NAME_ACTIVE_RECORD, diaryRecord);
    }
    
    public static String getContextPath (HttpSession session) {
        
        return (String) session.getAttribute(ATTR_NAME_CONTEXT_PATH);
    }
    
    public static void setContextPath (String contextPath, HttpSession session) {
        
        session.setAttribute(ATTR_NAME_CONTEXT_PATH, contextPath);
    }
    
}
