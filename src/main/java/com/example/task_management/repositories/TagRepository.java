package com.example.task_management.repositories;

import com.example.task_management.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    // Custom queries here if needed in the future
}
