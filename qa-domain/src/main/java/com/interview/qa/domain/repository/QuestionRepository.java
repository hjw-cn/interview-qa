package com.interview.qa.domain.repository;

import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.model.condition.QuestionsCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuestionRepository{
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
    List<Question> findQuestionByCondition(QuestionsCondition condition);

}
