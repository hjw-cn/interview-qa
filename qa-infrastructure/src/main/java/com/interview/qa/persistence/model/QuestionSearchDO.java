package com.interview.qa.persistence.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "question")
public class QuestionSearchDO {
    private Long id;
    private String content;
    private String answer;
}
