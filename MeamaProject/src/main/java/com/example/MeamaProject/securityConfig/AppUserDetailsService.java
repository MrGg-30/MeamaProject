package com.example.MeamaProject.securityConfig;

import com.example.MeamaProject.users.control.UserService;
import com.example.MeamaProject.users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUser(username);
        if(user == null){
            throw new SecurityException();
        }

        return new AppUserDetails(user);
    }
}
