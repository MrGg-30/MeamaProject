package com.example.MeamaProject.users.repository;

import com.example.MeamaProject.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);

    @Query(value = "SELECT DISTINCT user FROM User user WHERE user.username =:username")
    User fiiiindByUsername(String username);
}
