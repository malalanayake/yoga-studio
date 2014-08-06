package com.app.studio.service.impl;

import com.app.studio.dao.UserDAO;
import com.app.studio.model.User;
import com.app.studio.security.CustomRole;
import com.app.studio.security.CustomUserData;
import com.app.studio.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * CustomUserDetailsService provides the connection point to external data
 * source
 *
 * @author malalanayake
 *
 */
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String authentication) throws UsernameNotFoundException {
        CustomUserData customUserData = new CustomUserData();
        User user = userService.getUserByUserName(authentication);
        if (user != null) {
            customUserData.setAuthentication(true);
            customUserData.setId(Integer.toString(user.getId()));
            customUserData.setUserName(user.getUsername());
            customUserData.setPassword(user.getPassword());
            Collection<CustomRole> roles = new ArrayList<CustomRole>();
            for (String role : user.getRoles()) {
                CustomRole customRole = new CustomRole();
                customRole.setAuthority(role);
                roles.add(customRole);
            }
            customUserData.setAuthorities(roles);
            return customUserData;
        } else {
            return null;
        }
    }

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
