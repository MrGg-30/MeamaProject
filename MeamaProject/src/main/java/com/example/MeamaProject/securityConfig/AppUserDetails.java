package com.example.MeamaProject.securityConfig;

import com.example.MeamaProject.privileges.entity.Privilege;
import com.example.MeamaProject.roles.entity.Role;
import com.example.MeamaProject.users.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppUserDetails implements UserDetails {

    private final User user;

    public AppUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(getPrivileges(user.getRole()));
    }

    private List<String> getPrivileges(Role role) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        Set<Privilege> set = new HashSet<>(role.getPrivileges());

        if(role != null) {
            privileges.add("ROLE_" + role.getName());
            collection.addAll(set);
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}