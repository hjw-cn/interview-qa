package com.interview.qa.app.assembler;

import com.interview.qa.app.model.dto.QuestionDTO;
import com.interview.qa.domain.model.Question;

public class QuestionDTOAssembler {
    public static QuestionDTO toDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setType(question.getType());
        questionDTO.setContent(question.getContent());
        questionDTO.setLevel(question.getLevel());
        questionDTO.setUpdateTime(question.getUpdateTime());
        questionDTO.setCreateTime(question.getCreateTime());
        questionDTO.setTags(question.getTags());
        questionDTO.setAnswer(question.getAnswer());
        return questionDTO;
    }
}
