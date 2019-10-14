package com.owner.es.repository;

import com.owner.es.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author wangxl
 * @date 2019-10-10
 */
public interface PersonRepository extends ElasticsearchRepository<Person, String> {
    Page<Person> findByA01000(String id, Pageable pageable);
}
