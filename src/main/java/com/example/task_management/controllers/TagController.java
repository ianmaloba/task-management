package com.example.task_management.controllers;

import com.example.task_management.models.Tag;
import com.example.task_management.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public String getAllTags(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        return "tags";
    }

    @GetMapping("/create")
    public String createTagForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "create_tag";
    }

    @PostMapping("/create")
    public String createTag(@ModelAttribute Tag tag) {
        tagService.createTag(tag);
        return "redirect:/tags";
    }
}
