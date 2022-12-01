package com.interview.qa.domain.model.convert;

import cn.hutool.core.util.StrUtil;
import com.interview.qa.client.cqe.QuestionsQuery;
import com.interview.qa.domain.model.condition.QuestionsCondition;

public class QuestionConditionConvert {

    public static QuestionsCondition toQuestionCondition(QuestionsQuery questionsQuery) {
        QuestionsCondition questionsCondition = new QuestionsCondition();
        questionsCondition.setLimit(questionsQuery.getPageSize());
        // 根据page和pageSize计算offset
        questionsCondition.setOffset((questionsQuery.getPage() - 1) * questionsQuery.getPageSize());
        questionsCondition.setTags(questionsQuery.getTags());
        if (questionsQuery.getSortField() != null){
            String orderBy = StrUtil.concat(true, questionsQuery.getSortField(), " ", questionsQuery.getSortType());
            questionsCondition.setOrderBy(orderBy);
        }
        return questionsCondition;
    }
}
