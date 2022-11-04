package com.interview.qa.controller.model.builder;

import com.interview.qa.app.model.command.QuestionsQuery;
import com.interview.qa.controller.model.request.QuestionRequest;

public class QuestionRequestBuilder {

    public static QuestionsQuery buildQuestionsQuery(QuestionRequest questionRequest) {

        QuestionsQuery questionsQuery = new QuestionsQuery();
        questionsQuery.setPage(questionRequest.getPage());
        questionsQuery.setPageSize(questionRequest.getPageSize());
        questionsQuery.setTags(questionRequest.getTags());
        questionsQuery.setSortField(questionRequest.getSortField());
        questionsQuery.setSortType(questionRequest.getSortType());
        return questionsQuery;
    }
}
