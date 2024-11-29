package com.example.task_management.repositories;

import com.example.task_management.models.Tag;
import com.example.task_management.models.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Find tasks by their associated tag
    List<Task> findByTags(Tag tag);

    // Find tasks by title or description containing the search query (case-insensitive)
    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

}
