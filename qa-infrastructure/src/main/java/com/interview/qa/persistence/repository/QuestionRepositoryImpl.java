package com.interview.qa.persistence.repository;

import cn.hutool.core.lang.UUID;
import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.repository.QuestionRepository;
import com.interview.qa.persistence.convertor.QuestionBuilder;
import com.interview.qa.persistence.mapper.QuestionMapper;
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

    private final QuestionMapper questionMapper;
    private final QuestionSearchRepository questionSearchRepository;

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
        questionMapper.insert(questionDO);
        questionSearchRepository.save(questionSearchDO);
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
