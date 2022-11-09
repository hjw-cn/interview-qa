package com.interview.qa.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tag_question")
public class TagQuestionDO implements Serializable  {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private Long tagId;
    private String tagName;
    private Long questionId;
}
