/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reshetyk.alexey.diary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import reshetyk.alexey.diary.domain.DiaryUser;
import reshetyk.alexey.diary.service.DiaryUserService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexey
 */
public class SecurityService implements UserDetailsService{

    @Autowired
    private DiaryUserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        
        
        DiaryUser user = userService.getUserByLogin(username);
        
        if(user == null) return null;

        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new GrantedAuthorityImpl("ROLE_USER"));
        
        return new User(user.getLogin(), user.getPassword(), true, true, true, true, list);
    }
    
}
