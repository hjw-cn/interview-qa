package com.interview.qa.domain.model.condition;

import lombok.Data;

import java.util.List;

@Data
public class QuestionsCondition {
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 标签
     */
    private List<String> tags;
    /**
     * 排序字段
     */
    private String sortField;
    /**
     * 排序方式 asc：升序 desc：降序
     */
    private String sortType;
}
