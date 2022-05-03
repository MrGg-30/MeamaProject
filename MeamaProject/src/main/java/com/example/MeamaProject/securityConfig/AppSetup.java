package com.example.MeamaProject.securityConfig;

import com.example.MeamaProject.privileges.entity.Privilege;
import com.example.MeamaProject.privileges.repository.PrivilegeRepository;
import com.example.MeamaProject.roles.entity.Role;
import com.example.MeamaProject.roles.repository.RoleRepository;
import com.example.MeamaProject.users.entity.User;
import com.example.MeamaProject.users.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class AppSetup implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    public final BCryptPasswordEncoder passwordEncoder;

    public AppSetup(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        User user = userRepository.findByUsername("meamau");
        if(user == null){
            User meamaUser = new User();
            meamaUser.setUsername("meamau");
            meamaUser.setLastname("meamap");
            meamaUser.setFirstname("meamau");
            meamaUser.setPassword(passwordEncoder.encode("meamap"));
            Role role = roleRepository.findByName("ADMIN");
            Collection<Privilege> privileges = new ArrayList<>();
            privileges.add(privilegeRepository.findByName("TASK_CREATE"));
            privileges.add(privilegeRepository.findByName("TASK_DELETE"));
            privileges.add(privilegeRepository.findByName("TASK_UPDATE"));
            privileges.add(privilegeRepository.findByName("TASK_INSPECTION"));
            role.setPrivileges(privileges);
            meamaUser.setRole(role);
            userRepository.saveAndFlush(meamaUser);
        }
    }
}
