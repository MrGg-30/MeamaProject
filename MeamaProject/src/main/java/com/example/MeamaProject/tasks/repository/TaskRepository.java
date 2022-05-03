package com.example.MeamaProject.tasks.repository;

import com.example.MeamaProject.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);
}
