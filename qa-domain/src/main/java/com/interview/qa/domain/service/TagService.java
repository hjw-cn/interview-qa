package com.interview.qa.domain.service;

import com.interview.qa.domain.model.Tag;

import java.util.List;

public interface TagService {

    void deleteTagById(Long id);

    void insertTag(String tagName);

    List<Tag> findAllTag();
}
