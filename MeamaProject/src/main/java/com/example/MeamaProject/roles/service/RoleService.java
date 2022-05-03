package com.example.MeamaProject.roles.service;

import com.example.MeamaProject.privileges.entity.Privilege;
import com.example.MeamaProject.privileges.repository.PrivilegeRepository;
import com.example.MeamaProject.roles.boundary.RoleDTO;
import com.example.MeamaProject.roles.entity.Role;
import com.example.MeamaProject.roles.repository.RoleRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    public RoleService(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    public Long addRole(RoleDTO roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        List<Privilege> privilegeList = new ArrayList<>();
        if(roleDto.getPrivileges() != null) {
            for (String privilegeName : roleDto.getPrivileges()) {
                if (privilegeRepository.findByName(privilegeName) != null) {
                    if (!privilegeList.contains(privilegeRepository.findByName(privilegeName))) {
                        privilegeList.add(privilegeRepository.findByName(privilegeName));
                    }
                }
            }
        }
        role.setPrivileges(privilegeList);

        return roleRepository.saveAndFlush(role).getId();
    }
}
