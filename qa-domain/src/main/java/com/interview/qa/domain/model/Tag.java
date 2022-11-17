package com.interview.qa.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Tag {
  public Tag(String tagName){
    this.tagName = tagName;
  }
  private Long id;
  private String tagName;
}
