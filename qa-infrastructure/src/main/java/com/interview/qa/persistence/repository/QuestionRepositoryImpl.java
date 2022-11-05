package com.interview.qa.persistence.repository;

import cn.hutool.core.lang.UUID;
import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.model.condition.QuestionsCondition;
import com.interview.qa.domain.repository.QuestionRepository;
import com.interview.qa.persistence.convertor.QuestionBuilder;
import com.interview.qa.persistence.mapper.QuestionDOMapper;
import com.interview.qa.persistence.model.QuestionDO;
import com.interview.qa.persistence.model.QuestionSearchDO;
import com.interview.qa.persistence.search.QuestionSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionDOMapper questionDOMapper;
    private final QuestionSearchRepository questionSearchRepository;

    @Override
    public void deleteQuestionById(Long id) {
        questionDOMapper.deleteById(id);
    }

    @Override
    public List<Question> findAllQuestion() {
        List<QuestionDO> questionDOS = questionDOMapper.selectList(null);
        return questionDOS.stream().map(
                QuestionBuilder::toDomainObject
        ).collect(toList());
    }

    @Override
    public Question findQuestionById(Long id) {
        QuestionDO questionDO = questionDOMapper.selectById(id);
        Question question = QuestionBuilder.toDomainObject(questionDO);
        Optional<QuestionSearchDO> questionSearchDO = questionSearchRepository.findById(questionDO.getUid());
//        question.set
//        questionSearchRepository.fin
        return question;
    }

    @Override
    public void insertQuestion(Question question) {
        String uid = UUID.randomUUID().toString();
        question.setUid(uid);
        // 构建持久化对象
        QuestionDO questionDO = QuestionBuilder.toDataObject(question);
        QuestionSearchDO questionSearchDO = QuestionBuilder.toSearchDataObject(question);
        // 持久化到mysql和es
        questionDOMapper.insert(questionDO);
        questionSearchRepository.save(questionSearchDO);
    }

    @Override
    public void updateQuestion(Question question) {
        QuestionDO questionDO = QuestionBuilder.toDataObject(question);
        questionDOMapper.updateById(questionDO);
    }

    @Override
    public List<Question> findQuestionByCondition(QuestionsCondition condition) {
        List<QuestionDO> questionDOS = questionDOMapper.findQuestionsByCondition(condition.buildParams());
        return questionDOS.stream().map(
                QuestionBuilder::toDomainObject
        ).collect(toList());
    }
}
