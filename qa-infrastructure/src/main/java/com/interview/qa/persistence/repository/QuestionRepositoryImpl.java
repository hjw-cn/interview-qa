package com.interview.qa.persistence.repository;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.model.condition.QuestionsCondition;
import com.interview.qa.domain.repository.QuestionRepository;
import com.interview.qa.persistence.convertor.QuestionBuilder;
import com.interview.qa.persistence.mapper.QuestionDOMapper;
import com.interview.qa.persistence.mapper.TagQuestionDOMapper;
import com.interview.qa.persistence.model.QuestionDO;
import com.interview.qa.persistence.model.QuestionSearchDO;
import com.interview.qa.persistence.search.QuestionSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionDOMapper questionDOMapper;
    private final QuestionSearchRepository questionSearchRepository;
    private final TagQuestionDOMapper tagQuestionDOMapper;

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
    @Transactional
    public void updateQuestion(Question question) {
        // 先更新mysql 再更新es
        QuestionDO questionDO = QuestionBuilder.toDataObject(question);
        questionDOMapper.updateById(questionDO);

        QuestionDO questionById = questionDOMapper.selectById(question.getId());
        if (question.getAnswer() != null) {
            QuestionSearchDO questionSearchDO = QuestionBuilder.toSearchDataObject(questionById);
            questionSearchRepository.save(questionSearchDO);
        }
    }

    @Override
    public List<Question> findQuestionByCondition(QuestionsCondition condition) {
        List<Long> questionIdsByTags = findQuestionIdByTags(condition.getTags());
        if (CollectionUtil.isEmpty(questionIdsByTags)) {
            return Collections.emptyList();
        }
        condition.setQuestionIds(questionIdsByTags);
        List<QuestionDO> questionDOS = questionDOMapper.findQuestionsByCondition(condition.buildParams());
        return questionDOS.stream().map(
                QuestionBuilder::toDomainObject
        ).collect(toList());
    }

    @Override
    public List<Long> findQuestionIdByTags(List<String> tags) {
        if (CollectionUtil.isEmpty(tags)) return Collections.emptyList();
        return tagQuestionDOMapper.findQuestionIdsByTags(tags);
    }
}
