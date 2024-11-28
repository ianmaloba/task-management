package com.example.task_management.services;

import com.example.task_management.models.Task;
import com.example.task_management.models.Tag;
import com.example.task_management.models.User;
import com.example.task_management.repositories.TaskRepository;
import com.example.task_management.repositories.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;
    private final AuditService auditService;

    public TaskService(TaskRepository taskRepository, TagRepository tagRepository, AuditService auditService) {
        this.taskRepository = taskRepository;
        this.tagRepository = tagRepository;
        this.auditService = auditService;
    }

    public Task createTask(Task task, User user) {
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        auditService.logAction("CREATE", "Task", savedTask.getId(), "Task created", user);
        return savedTask;
    }

    public Task updateTask(Task task, User user) {
        Task existingTask = taskRepository.findById(task.getId())
            .orElseThrow(() -> new RuntimeException("Task not found"));
            
        if (!existingTask.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to update this task");
        }
        
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());
        
        Task updatedTask = taskRepository.save(existingTask);
        auditService.logAction("UPDATE", "Task", updatedTask.getId(), "Task updated", user);
        return updatedTask;
    }

    public void deleteTask(Long taskId, User user) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));
            
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to delete this task");
        }
        
        taskRepository.delete(task);
        auditService.logAction("DELETE", "Task", taskId, "Task deleted", user);
    }
}