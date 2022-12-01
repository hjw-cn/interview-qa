package com.interview.qa.domain.service.impl;

import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.model.condition.QuestionsCondition;
import com.interview.qa.domain.repository.QuestionRepository;
import com.interview.qa.domain.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    public void resolveExcelAndSave(MultipartFile file) {
        questionRepository.resolveExcelAndSave(file);
    }

    @Override
    public Boolean saveQuestionFile(MultipartFile file) {
        return null;
    }

    @Override
    public Integer saveQuestionList(List<Question> questionList) {
        return questionRepository.batchInsertQuestion(questionList);
    }
}
