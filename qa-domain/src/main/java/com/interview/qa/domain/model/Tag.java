package com.interview.qa.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tag {
  public Tag(String tagName){
    this.tagName = tagName;
  }
  private Long id;
  private String tagName;
}
