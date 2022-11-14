package com.interview.qa.domain.service;

import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.model.condition.QuestionsCondition;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface QuestionService {

    /**
     * 根据id删除问题
     */
    void deleteQuestionById(Long id);

    /**
     * 查询所有问题
     */
    List<Question> findAllQuestion();

    /**
     * 根据id查询问题
     */
    Question findQuestionById(Long id);

    /**
     * 新增问题
     */
    void insertQuestion(Question question);

    /**
     * 更新问题
     */
    void updateQuestion(Question question);

    /**
     * 根据标签查询问题
     */
    List<Question> findQuestionsByCondition(QuestionsCondition condition);

    Boolean resolveExcelAndSave(MultipartFile file);

    Boolean saveQuestionFile(MultipartFile file);
}
