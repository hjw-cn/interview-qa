package com.interview.qa.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Question implements Serializable {
    private Long id;
    private String type;
    private String content;
    private Long level;
    private String updateTime;
    private String createTime;
    private Long isDelete;
    private String tags;
}
