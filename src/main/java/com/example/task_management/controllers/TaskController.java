package com.example.task_management.controllers;

import com.example.task_management.models.Tag;
import com.example.task_management.models.Task;
import com.example.task_management.services.TaskService;
import com.example.task_management.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/create")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("tags", tagService.getAllTags());
        return "create_task";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task, @RequestParam(value = "tags", required = false) List<Long> tagIds) {
        if (tagIds != null) {
            Set<Tag> tags = new HashSet<>();
            for (Long tagId : tagIds) {
                Tag tag = tagService.getTagById(tagId);
                tags.add(tag);
            }
            task.setTags(tags);  // Set the tags on the task
        }
        taskService.createTask(task);  // Save the task with tags
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("tags", tagService.getAllTags());
        return "edit_task";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, @ModelAttribute Task task, @RequestParam(value = "tags", required = false) List<Long> tagIds) {
        if (tagIds != null) {
            Set<Tag> tags = new HashSet<>();
            for (Long tagId : tagIds) {
                Tag tag = tagService.getTagById(tagId);
                tags.add(tag);
            }
            task.setTags(tags);  // Set the tags on the task
        }
        taskService.updateTask(id, task);  // Update the task with new tags
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    // Method to toggle task completion
    @PostMapping("/toggle/{id}")
    public String toggleTaskCompletion(@PathVariable Long id) {
        taskService.toggleTaskCompletion(id);
        return "redirect:/tasks";
    }

    // Display individual task by ID
    @GetMapping("/{id}")
    public String viewTask(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            model.addAttribute("task", task);
            return "task_detail";
        } else {
            return "redirect:/tasks"; // If the task doesn't exist, redirect to the task list
        }
    }

    // Search tasks by query
    @GetMapping("/search")
    public String searchTasks(@RequestParam("query") String query, Model model) {
        List<Task> tasks = taskService.searchTasks(query);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
}
