package com.interview.qa.domain.repository;

import com.interview.qa.domain.model.Tag;

import java.util.List;

public interface TagRepository {

    /**
     * 删除标签
     */
    void deleteTag();

    /**
     * 查询所有标签
     */
    List<Tag> findAllTag();

    /**
     * 根据id查询标签
     */
    Tag findTagById(Long id);

    /**
     * 新增标签
     */
    void insertTag(Tag tag);
}
