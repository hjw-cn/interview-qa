package com.interview.qa.domain.model.condition;

import lombok.Data;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@Setter
public class QuestionsCondition {
    /**
     * offset
     */
    private Integer offset;
    /**
     * 每页条数
     */
    private Integer limit;
    /**
     * 标签
     */
    private List<String> tags;
    /**
     * 排序字段
     */
    private String orderBy;

    private List<Long> questionIds;

    public QuestionsCondition buildParams() {
        if (this.limit == null) {
            this.limit = 15;
        }
        if (this.offset == null) {
            this.offset = 1;
        }
        this.orderBy = StringUtils.isEmpty(this.orderBy) ?"id asc":orderBy;
        return this;
    }
}
