package com.example.task_management.services;

import com.example.task_management.models.Tag;
import com.example.task_management.repositories.TagRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag getOrCreateTag(String name) {
        return tagRepository.findByName(name)
            .orElseGet(() -> {
                Tag newTag = new Tag();
                newTag.setName(name);
                return tagRepository.save(newTag);
            });
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
