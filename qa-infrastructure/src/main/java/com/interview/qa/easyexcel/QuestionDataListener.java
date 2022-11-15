package com.interview.qa.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.repository.QuestionRepository;
import com.interview.qa.easyexcel.data.QuestionExcelData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class QuestionDataListener implements ReadListener<QuestionExcelData> {

    private static final int BATCH_SIZE = 50;
    private final QuestionRepository questionRepository;
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

    public void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedQuestionList.size());
        total += cachedQuestionList.size();
        Integer partSuccess = questionRepository.batchInsertQuestion(cachedQuestionList);
        success += partSuccess;
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
