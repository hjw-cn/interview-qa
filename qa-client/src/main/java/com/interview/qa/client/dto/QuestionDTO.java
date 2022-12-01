package com.interview.qa.client.dto;

import lombok.Data;

/**
 * DTO对象只是数据容器，只是为了和外部交互，所以本身不包含任何逻辑，只是贫血对象。
 */
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
