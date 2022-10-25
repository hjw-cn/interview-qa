package com.interview.qa.app.service.question;

import com.interview.qa.app.model.dto.TagDTO;
import com.interview.qa.domain.model.Tag;
import com.interview.qa.domain.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagAppService {
    private final TagService tagService;

    public void insertTag(String tagName) {
        tagService.insertTag(tagName);
    }

    public void deleteTagById(Long id) {
        tagService.deleteTagById(id);
    }

    public List<TagDTO> getAllTag() {
        List<Tag> allTag = tagService.findAllTag();
        return allTag.stream().map(tag -> {
            TagDTO tagDTO = new TagDTO();
            tagDTO.setId(tag.getId());
            tagDTO.setTagName(tag.getTagName());
            return tagDTO;
        }).collect(Collectors.toList());
    }
}
