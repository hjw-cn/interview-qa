package com.interview.qa.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.model.Tag;
import com.interview.qa.domain.repository.QuestionRepository;
import com.interview.qa.domain.repository.TagRepository;
import com.interview.qa.easyexcel.data.QuestionExcelData;
import com.interview.qa.persistence.mapper.TagQuestionDOMapper;
import com.interview.qa.persistence.model.TagQuestionDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
public class QuestionDataListener implements ReadListener<QuestionExcelData> {

    private static final int BATCH_SIZE = 50;
    private final QuestionRepository questionRepository;

    private final TagRepository tagRepository;
    private final TagQuestionDOMapper tagQuestionDOMapper;
    private List<Question> cachedQuestionList = ListUtils.newArrayListWithExpectedSize(BATCH_SIZE);

    private Integer total=0;
    private Integer success=0;

    @Override
    public void invoke(QuestionExcelData questionExcelData, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", questionExcelData);
        cachedQuestionList.add(excelDataToQuestion(questionExcelData));
        if (cachedQuestionList.size() >= BATCH_SIZE) {
            saveData();
            cachedQuestionList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！Excel共解析{}条数据，{}条存储成功~", total, success);
    }

    /**
     * 保存解析出来的数据
     */
    @Transactional
    public void saveData() {
        List<String> tagList = tagRepository.findAllTagName();
        log.info("{}条数据，开始存储数据库！", cachedQuestionList.size());
        total += cachedQuestionList.size();
        // 存储数据
        AtomicInteger partSuccess = new AtomicInteger(0);
        // 存储标签关系
        cachedQuestionList.forEach(question -> {
            question.setId(questionRepository.insertQuestion(question));
            if (question.getId()!=null) {
                partSuccess.incrementAndGet();
            }
            List<String> questionTags = List.of(question.getTags().split(","));
            questionTags.forEach(tag -> {
                // 如果标签不存在 则新建标签
                if (!tagList.contains(tag)) {
                    tagRepository.insertTag(new Tag(tag));
                    tagList.add(tag);
                }
                // 存储标签-问题关系
                TagQuestionDO tagQuestionDO = new TagQuestionDO();
                tagQuestionDO.setQuestionId(question.getId());
                tagQuestionDO.setTagId(tagRepository.findTagByName(tag).getId());
                tagQuestionDO.setTagName(tag);
                tagQuestionDOMapper.insert(tagQuestionDO);
            });
        });
        success += partSuccess.get();
        log.info("存储数据库结果：共{}条数据，{}条成功~", cachedQuestionList.size(), partSuccess);
    }

    public Question excelDataToQuestion(QuestionExcelData questionExcelData) {
        Question question = new Question();
        question.setLevel(questionExcelData.getLevel());
        question.setAnswer(questionExcelData.getAnswer());
        question.setContent(questionExcelData.getContent());
        question.setType(questionExcelData.getType());
        question.setTags(questionExcelData.getTags());
        return question;
    }
}
