package com.interview.qa.controller.resource;

import com.alibaba.cola.dto.Response;
import com.interview.qa.app.service.question.TagAppService;
import com.interview.qa.controller.model.builder.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class QuestionTagController {
    private final TagAppService tagAppService;

    @GetMapping("/insert")
    public Response insertTag(@RequestParam String tagName) {
        tagAppService.insertTag(tagName);
        return Response.buildSuccess();
    }

    @GetMapping("/{id}/delete")
    public Response deleteTagById(@PathVariable Long id) {
        tagAppService.deleteTagById(id);
        return Response.buildSuccess();
    }

    @GetMapping("/all")
    public Response getAllTag() {
        tagAppService.getAllTag();
        return ResponseBuilder.withMulti(true, "200", "success", tagAppService.getAllTag());
    }
}
