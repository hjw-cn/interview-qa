package com.interview.qa.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TagDO {
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  private String tagName;
}
