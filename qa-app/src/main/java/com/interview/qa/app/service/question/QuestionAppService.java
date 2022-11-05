package com.interview.qa.app.service.question;

import cn.hutool.core.util.StrUtil;
import com.interview.qa.app.model.command.QuestionsQuery;
import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.model.condition.QuestionsCondition;
import com.interview.qa.domain.service.QuestionService;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionAppService {
    private final QuestionService questionService;


    public void deleteQuestionById(Long id) {
        questionService.deleteQuestionById(id);
    }

    public void insertQuestion(Question question) {
        questionService.insertQuestion(question);
    }

    public void updateQuestion(Question question) {
        questionService.updateQuestion(question);
    }

    public List<Question> findAllQuestion(QuestionsQuery questionsQuery) {

        return questionService.findAllQuestion();
    }

    public Question findQuestionById(Long id) {
        return questionService.findQuestionById(id);
    }

    public List<Question> findQuestions(QuestionsQuery query) {

        return questionService.findQuestionsByCondition(toQuestionCondition(query));
    }


    private QuestionsCondition toQuestionCondition(QuestionsQuery questionsQuery) {
        QuestionsCondition questionsCondition = new QuestionsCondition();
        questionsCondition.setLimit(questionsQuery.getPageSize());
        // 根据page和pageSize计算offset
        questionsCondition.setOffset((questionsQuery.getPage() - 1) * questionsQuery.getPageSize());
        questionsCondition.setTags(questionsQuery.getTags());
        if (questionsQuery.getSortField() != null){
            String orderBy = StrUtil.concat(true, questionsQuery.getSortField(), " ", questionsQuery.getSortField());
            questionsCondition.setOrderBy(orderBy);
        }
        return questionsCondition;
    }
}
