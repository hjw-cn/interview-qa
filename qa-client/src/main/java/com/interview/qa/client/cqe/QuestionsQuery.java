package com.interview.qa.client.cqe;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * CQE对象是ApplicationService的输入，是有明确的”意图“的，所以这个对象必须保证其”正确性“。
 * CQE：command query event 三个对象的意图是不同的，所以这三个对象的属性也是不同的。
 * 根据单个id查询这种情况是例外的，不需要构造CQE对象，直接传入id即可。
 */
@Data
@Accessors(chain = true)
public class QuestionsQuery {

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

    private List<Long> questionIds;

}
