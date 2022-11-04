package com.interview.qa.app.service.question;

import com.interview.qa.app.model.command.QuestionsQuery;
import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.model.condition.QuestionsCondition;
import com.interview.qa.domain.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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



    private QuestionsCondition toQuestionCondition(QuestionsQuery questionsQuery){
        QuestionsCondition questionsCondition = new QuestionsCondition();
        questionsCondition.setPage(questionsQuery.getPage());
        questionsCondition.setPageSize(questionsQuery.getPageSize());
        questionsCondition.setTags(questionsQuery.getTags());
        questionsCondition.setSortField(questionsQuery.getSortField());
        questionsCondition.setSortType(questionsQuery.getSortType());
        return questionsCondition;
    }
}
