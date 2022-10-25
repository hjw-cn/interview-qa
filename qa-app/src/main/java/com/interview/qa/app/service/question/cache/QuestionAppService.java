package com.interview.qa.app.service.question.cache;

import com.interview.qa.domain.model.Question;
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

    public List<Question> findAllQuestion() {
        return questionService.findAllQuestion();
    }

    public Question findQuestionById(Long id) {
        return questionService.findQuestionById(id);
    }

    public List<Question> findQuestionByTag(String tag) {
        return questionService.findQuestionByTag(tag);
    }

}
