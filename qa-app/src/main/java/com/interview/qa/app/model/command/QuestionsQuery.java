package com.interview.qa.app.model.command;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class QuestionsQuery {

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
