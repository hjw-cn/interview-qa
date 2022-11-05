package com.interview.qa.persistence.repository;


import com.interview.qa.domain.model.Tag;
import com.interview.qa.domain.repository.TagRepository;
import com.interview.qa.persistence.mapper.TagDOMapper;
import com.interview.qa.persistence.model.TagDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {

    private final TagDOMapper tagDOMapper;

    @Override
    public void deleteTagById(Long id) {
        tagDOMapper.deleteById(id);
    }

    @Override
    public List<Tag> findAllTag() {
        List<TagDO> tagDOS = tagDOMapper.selectList(null);
        // 转为领域对象
        return tagDOS.stream().map(tagDO -> {
            Tag tag = new Tag();
            tag.setId(tagDO.getId());
            tag.setTagName(tagDO.getTagName());
            return tag;
        }).collect(Collectors.toList());
    }

    @Override
    public Tag findTagById(Long id) {
        return null;
    }

    @Override
    public void insertTag(Tag tag) {
        // TODO Auto-generated method stub
        TagDO tagDO = new TagDO();
        tagDO.setTagName(tag.getTagName());
        tagDOMapper.insert(tagDO);
    }

}
