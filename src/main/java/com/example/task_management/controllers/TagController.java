package com.example.task_management.controllers;

import com.example.task_management.models.Tag;
import com.example.task_management.models.Task;
import com.example.task_management.services.TagService;
import com.example.task_management.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TaskService taskService;

    // Display all tags
    @GetMapping
    public String listTags(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        return "tags";
    }

    // Show form for creating a new tag
    @GetMapping("/create")
    public String showCreateTagForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "create_tag";
    }

    // Handle new tag creation
    @PostMapping("/create")
    public String createTag(@ModelAttribute Tag tag) {
        tagService.saveTag(tag);
        return "redirect:/tags";
    }

    // Show form for editing an existing tag
    @GetMapping("/edit/{id}")
    public String showEditTagForm(@PathVariable Long id, Model model) {
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag", tag);
        return "edit_tag";
    }

    // Handle editing an existing tag
    @PostMapping("/edit/{id}")
    public String editTag(@PathVariable Long id, @ModelAttribute Tag tag) {
        tag.setId(id);
        tagService.saveTag(tag);
        return "redirect:/tags";
    }

    // Display tasks associated with a specific tag
    @GetMapping("/{id}")
    public String viewTagTasks(@PathVariable Long id, Model model) {
        Tag tag = tagService.getTagById(id);
        List<Task> tasks = taskService.getTasksByTag(tag);
        model.addAttribute("tag", tag);
        model.addAttribute("tasks", tasks);
        return "tag_tasks";
    }
}
