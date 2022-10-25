package com.interview.qa.controller.resource;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.interview.qa.app.service.question.QuestionAppService;
import com.interview.qa.controller.model.builder.ResponseBuilder;
import com.interview.qa.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qa")
@RequiredArgsConstructor
public class QuestionAnswerController {

    private final QuestionAppService questionAppService;

    @GetMapping("/questions")
    public MultiResponse<Question> getQuestions() {
        List<Question> allQuestion = questionAppService.findAllQuestion();
        return ResponseBuilder.withMulti(true, "200", "success", allQuestion);
    }

    @GetMapping("/question/{id}")
    public Response getQuestionById(@PathVariable Long id) {
        Question questionById = questionAppService.findQuestionById(id);
        return ResponseBuilder.with(true, "200", "success", questionById);
    }

    @GetMapping("/question/tag/{tag}")
    public MultiResponse<Question> getQuestionByTag(@PathVariable String tag) {
        List<Question> questionByTag = questionAppService.findQuestionByTag(tag);
        return ResponseBuilder.withMulti(true, "200", "success", questionByTag);
    }

    @PostMapping("/question/insert")
    public Response addQuestion(@RequestBody Question question) {
        questionAppService.insertQuestion(question);
        return ResponseBuilder.with(true, "200", "success", null);
    }

    @PutMapping("/question/update")
    public Response updateQuestion(@RequestBody Question question) {
        questionAppService.updateQuestion(question);
        return ResponseBuilder.with(true, "200", "success", null);
    }

    @DeleteMapping("/question/delete/{id}")
    public Response deleteQuestion(@PathVariable Long id) {
        questionAppService.deleteQuestionById(id);
        return ResponseBuilder.with(true, "200", "success", null);
    }
}
