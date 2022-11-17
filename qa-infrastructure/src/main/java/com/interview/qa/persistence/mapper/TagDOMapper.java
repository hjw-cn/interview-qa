package com.interview.qa.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.interview.qa.persistence.model.TagDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagDOMapper extends BaseMapper<TagDO> {
    public List<String> findAllTagName();
}
