package com.interview.qa.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.interview.qa.persistence.model.QuestionDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<QuestionDO> {
}

