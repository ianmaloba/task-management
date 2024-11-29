package com.example.task_management.services;

import com.example.task_management.models.Tag;
import com.example.task_management.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // Get all tags
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    // Get tag by ID
    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    // Create a new tag
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    // Update an existing tag
    public Tag updateTag(Tag tag) {
        return tagRepository.save(tag);
    }

    // Delete a tag
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

}
