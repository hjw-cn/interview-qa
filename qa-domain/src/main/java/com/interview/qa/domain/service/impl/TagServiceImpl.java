package com.interview.qa.domain.service.impl;

import com.interview.qa.domain.model.Tag;
import com.interview.qa.domain.repository.TagRepository;
import com.interview.qa.domain.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    @Override
    public void deleteTagById(Long id) {
        tagRepository.deleteTagById(id);
    }

    @Override
    public void insertTag(String tagName) {
        Tag tag = new Tag();
        tag.setTagName(tagName);
        tagRepository.insertTag(tag);
    }

    @Override
    public List<Tag> findAllTag() {
        return tagRepository.findAllTag();
    }
}
