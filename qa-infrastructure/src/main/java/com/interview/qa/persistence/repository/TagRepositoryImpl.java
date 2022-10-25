package com.interview.qa.persistence.repository;


import com.interview.qa.domain.model.Tag;
import com.interview.qa.domain.repository.TagRepository;
import com.interview.qa.persistence.mapper.TagMapper;
import com.interview.qa.persistence.model.TagDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {

    private final TagMapper tagMapper;

    @Override
    public void deleteTagById(Long id) {
        tagMapper.deleteById(id);
    }

    @Override
    public List<Tag> findAllTag() {
        List<TagDO> tagDOS = tagMapper.selectList(null);
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
        tagMapper.insert(tagDO);
    }

}
