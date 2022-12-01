package com.interview.qa.app.service.question;

import cn.hutool.core.util.StrUtil;
import com.interview.qa.client.cqe.QuestionsQuery;
import com.interview.qa.domain.model.Question;
import com.interview.qa.domain.model.condition.QuestionsCondition;
import com.interview.qa.domain.repository.QuestionRepository;
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

    private final QuestionRepository questionRepository;

    public void deleteQuestionById(Long id) {
        questionRepository.deleteQuestionById(id);
    }

    public void insertQuestion(Question question) {
        questionRepository.insertQuestion(question);
    }

    public void updateQuestion(Question question) {
        questionRepository.updateQuestion(question);
    }

    public List<Question> findAllQuestion(QuestionsQuery questionsQuery) {
        return questionRepository.findAllQuestion();
    }

    public Question findQuestionById(Long id) {
        return questionRepository.findQuestionById(id);
    }

    public List<Question> findQuestions(QuestionsQuery query) {
        return questionRepository.findQuestions(query);
    }


    /**
     * 从excel文件导入数据
     *
     * @param file excel文件
     */
    public void importQuestionFromExcel(MultipartFile file) {

        // 解析并保存数据
        CompletableFuture<Void> resolveFileFuture = CompletableFuture.runAsync(() -> {
            questionService.resolveExcelAndSave(file);
        });
        // 保存文件到oss
        CompletableFuture<Boolean> saveFileFuture = CompletableFuture.supplyAsync(() -> questionService.saveQuestionFile(file));
        try {
            saveFileFuture.get();
            resolveFileFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("importQuestionFromExcel error", e);
        }

    }
}
