package com.example.MeamaProject.privileges.repository;

import com.example.MeamaProject.privileges.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String privilege);
}
