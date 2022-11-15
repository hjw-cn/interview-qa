package com.interview.qa.easyexcel.data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionExcelData implements Serializable{
    @ExcelProperty("type")
    private String type;
    @ExcelProperty("content")
    private String content;
    @ExcelProperty("level")
    private Long level;
    @ExcelProperty("tags")
    private String tags;
    @ExcelProperty("answer")
    private String answer;
}
