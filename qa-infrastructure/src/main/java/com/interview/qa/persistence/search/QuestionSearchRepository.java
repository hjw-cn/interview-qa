package com.interview.qa.persistence.search;

import com.interview.qa.persistence.model.QuestionSearchDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionSearchRepository extends ElasticsearchRepository<QuestionSearchDO,String> {
}
