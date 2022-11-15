package com.interview.qa.app.service.question;

import cn.hutool.core.util.StrUtil;
import com.interview.qa.app.model.command.QuestionsQuery;
import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.model.condition.QuestionsCondition;
import com.interview.qa.domain.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionAppService {
    private final QuestionService questionService;


    public void deleteQuestionById(Long id) {
        questionService.deleteQuestionById(id);
    }

    public void insertQuestion(Question question) {
        questionService.insertQuestion(question);
    }

    public void updateQuestion(Question question) {
        questionService.updateQuestion(question);
    }

    public List<Question> findAllQuestion(QuestionsQuery questionsQuery) {

        return questionService.findAllQuestion();
    }

    public Question findQuestionById(Long id) {
        return questionService.findQuestionById(id);
    }

    public List<Question> findQuestions(QuestionsQuery query) {

        return questionService.findQuestionsByCondition(toQuestionCondition(query));
    }


    private QuestionsCondition toQuestionCondition(QuestionsQuery questionsQuery) {
        QuestionsCondition questionsCondition = new QuestionsCondition();
        questionsCondition.setLimit(questionsQuery.getPageSize());
        // 根据page和pageSize计算offset
        questionsCondition.setOffset((questionsQuery.getPage() - 1) * questionsQuery.getPageSize());
        questionsCondition.setTags(questionsQuery.getTags());
        if (questionsQuery.getSortField() != null){
            String orderBy = StrUtil.concat(true, questionsQuery.getSortField(), " ", questionsQuery.getSortType());
            questionsCondition.setOrderBy(orderBy);
        }
        return questionsCondition;
    }

    /**
     * 从excel文件导入数据
     *
     * @param file excel文件
     */
    public void importQuestionFromExcel(MultipartFile file) {
//        new File("C:\\Users\\Administrator\\Desktop\\test.xlsx");
        // 1. domain: 解析excel，批量存入数据
//        questionService.resolveExcelAndSave(file);
//        // 2. domain: 保存excel到cos
//        questionService.saveQuestionFile(file);


        CompletableFuture<Void> resolveFileFuture = CompletableFuture.runAsync(() -> {
            questionService.resolveExcelAndSave(file);
        });

        CompletableFuture<Boolean> saveFileFuture = CompletableFuture.supplyAsync(() -> {
            return questionService.saveQuestionFile(file);
        });
        try {
            saveFileFuture.get();
            resolveFileFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("importQuestionFromExcel error", e);
        }

    }
}
