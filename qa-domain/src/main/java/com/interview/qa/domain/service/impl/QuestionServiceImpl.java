package com.interview.qa.domain.service.impl;

import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.repository.QuestionRepository;
import com.interview.qa.domain.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public void deleteQuestionById(Long id) {
        questionRepository.deleteQuestionById(id);
    }

    @Override
    public List<Question> findAllQuestion() {
        return questionRepository.findAllQuestion();
    }

    @Override
    public Question findQuestionById(Long id) {
        return questionRepository.findQuestionById(id);
    }

    @Override
    public void insertQuestion(Question question) {
        questionRepository.insertQuestion(question);
    }

    @Override
    public void updateQuestion(Question question) {
        questionRepository.updateQuestion(question);
    }

    @Override
    public List<Question> findQuestionByTag(String tag) {
        return questionRepository.findQuestionByTag(tag);
    }
}
