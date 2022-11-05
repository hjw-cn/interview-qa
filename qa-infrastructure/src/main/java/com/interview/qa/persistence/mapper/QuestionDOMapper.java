package com.interview.qa.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.interview.qa.domain.model.condition.QuestionsCondition;
import com.interview.qa.persistence.model.QuestionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionDOMapper extends BaseMapper<QuestionDO> {

    List<QuestionDO> findQuestionsByCondition(QuestionsCondition condition);
}

