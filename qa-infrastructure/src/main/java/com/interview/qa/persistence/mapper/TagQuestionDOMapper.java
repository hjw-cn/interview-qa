package com.interview.qa.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.interview.qa.persistence.model.TagQuestionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagQuestionDOMapper extends BaseMapper<TagQuestionDO> {
    List<Long> findQuestionIdsByTags(@Param("tagNames") List<String> tags);
}
