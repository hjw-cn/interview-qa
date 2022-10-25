package com.interview.qa.persistence.repository;

import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.repository.QuestionRepository;
import com.interview.qa.persistence.convertor.QuestionBuilder;
import com.interview.qa.persistence.mapper.QuestionMapper;
import com.interview.qa.persistence.model.QuestionDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionMapper questionMapper;

    @Override
    public void deleteQuestionById(Long id) {
        questionMapper.deleteById(id);
    }

    @Override
    public List<Question> findAllQuestion() {
        List<QuestionDO> questionDOS = questionMapper.selectList(null);
        return questionDOS.stream().map(
                QuestionBuilder::toDomainObject
        ).collect(toList());
    }

    @Override
    public Question findQuestionById(Long id) {
        QuestionDO questionDO = questionMapper.selectById(id);
        return QuestionBuilder.toDomainObject(questionDO);
    }

    @Override
    public void insertQuestion(Question question) {
        QuestionDO questionDO = QuestionBuilder.toDataObject(question);
        questionMapper.insert(questionDO);
    }

    @Override
    public void updateQuestion(Question question) {
        QuestionDO questionDO = QuestionBuilder.toDataObject(question);
        questionMapper.updateById(questionDO);
    }

    @Override
    public List<Question> findQuestionByTag(String tag) {
//        List<QuestionDO> questionDOS = questionMapper.findQuestionByTag(tag);
//        return questionDOS.stream().map(
//                QuestionBuilder::toDomainObject
//        ).collect(toList());
        return null;
    }
}
