package com.interview.qa.persistence.repository;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.excel.EasyExcel;
import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.model.condition.QuestionsCondition;
import com.interview.qa.domain.repository.QuestionRepository;
import com.interview.qa.domain.repository.TagRepository;
import com.interview.qa.easyexcel.QuestionDataListener;
import com.interview.qa.easyexcel.data.QuestionExcelData;
import com.interview.qa.persistence.convertor.QuestionBuilder;
import com.interview.qa.persistence.mapper.QuestionDOMapper;
import com.interview.qa.persistence.mapper.TagQuestionDOMapper;
import com.interview.qa.persistence.model.QuestionDO;
import com.interview.qa.persistence.model.QuestionSearchDO;
import com.interview.qa.persistence.search.QuestionSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionDOMapper questionDOMapper;
    private final QuestionSearchRepository questionSearchRepository;
    private final TagQuestionDOMapper tagQuestionDOMapper;

    private final TagRepository tagRepository;

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
    public Long insertQuestion(Question question) {
        // 设置唯一id
        question.setUid(UUID.randomUUID().toString());
        // 构建持久化对象
        QuestionDO questionDO = QuestionBuilder.toDataObject(question);
        QuestionSearchDO questionSearchDO = QuestionBuilder.toSearchDataObject(question);
        // 持久化到mysql和es
        questionDOMapper.insert(questionDO);
        questionSearchRepository.save(questionSearchDO);
        return questionDO.getId();
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

    @Override
    public Integer batchInsertQuestion(List<Question> questionList) {
        AtomicInteger success = new AtomicInteger();
        questionList.forEach(question -> {
            if (insertQuestion(question) != null) {
                success.incrementAndGet();
            }
        });

        return success.get();
    }

    @Override
    public void resolveExcelAndSave(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), QuestionExcelData.class, new QuestionDataListener(this, tagRepository, tagQuestionDOMapper)).sheet().doRead();
        } catch (IOException e) {
            log.error("解析excel文件失败", e);
        }
    }
}
