package com.interview.qa.persistence.convertor;

import com.interview.qa.domain.model.Question;
import com.interview.qa.persistence.model.QuestionDO;
import com.interview.qa.persistence.model.QuestionSearchDO;

public class QuestionBuilder {

    public static Question toDomainObject(QuestionDO questionDO) {
        Question question = new Question();
        question.setId(questionDO.getId());
        question.setType(questionDO.getType());
        question.setContent(questionDO.getContent());
        question.setLevel(questionDO.getLevel());
        question.setUpdateTime(questionDO.getUpdateTime());
        question.setCreateTime(questionDO.getCreateTime());
        question.setIsDelete(questionDO.getIsDelete());
        question.setTags(questionDO.getTags());
        question.setAnswer(questionDO.getAnswer());
        question.setUid(questionDO.getUid());
        return question;
    }

    public static QuestionDO toDataObject(Question question) {
        QuestionDO questionDO = new QuestionDO();
        questionDO.setId(question.getId());
        questionDO.setType(question.getType());
        questionDO.setContent(question.getContent());
        questionDO.setLevel(question.getLevel());
        questionDO.setUpdateTime(question.getUpdateTime());
        questionDO.setCreateTime(question.getCreateTime());
        questionDO.setIsDelete(question.getIsDelete());
        questionDO.setTags(question.getTags());
        questionDO.setAnswer(question.getAnswer());
        questionDO.setUid(question.getUid());
        return questionDO;
    }

    public static QuestionSearchDO toSearchDataObject(Question question) {
        QuestionSearchDO questionSearchDO = new QuestionSearchDO();
        // mysql中的uid对应es中的id
        questionSearchDO.setId(question.getUid());
        questionSearchDO.setContent(question.getContent());
        questionSearchDO.setAnswer(question.getAnswer());
        return questionSearchDO;
    }
}
