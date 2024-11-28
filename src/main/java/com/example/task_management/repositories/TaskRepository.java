package com.example.task_management.repositories;

import com.example.task_management.models.Task;
import com.example.task_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByUserAndCompleted(User user, boolean completed);
    
    @Query("SELECT t FROM Task t JOIN t.tags tag WHERE tag.name = :tagName AND t.user = :user")
    List<Task> findByTagNameAndUser(String tagName, User user);
}