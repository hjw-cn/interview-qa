package com.interview.qa.app.model.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private String type;
    private String content;
    private Long level;
    private String updateTime;
    private String createTime;
    private String tags;
    private String answer;
}
