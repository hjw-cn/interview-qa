package com.interview.qa.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("question")
public class QuestionDO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String type;
    private String content;
    private Long level;
    private String updateTime;
    private String createTime;
    private Long isDelete;
    private String tags;
    private String answer;
    private String uid;
}
