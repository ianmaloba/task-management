package com.example.task_management.repositories;

import com.example.task_management.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
    
    @Query("SELECT t, COUNT(task) FROM Tag t LEFT JOIN t.tasks task GROUP BY t")
    List<Object[]> findAllWithTaskCount();
}