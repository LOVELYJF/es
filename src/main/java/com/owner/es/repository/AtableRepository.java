package com.owner.es.repository;

import com.owner.es.model.ATable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 接口关系：
 * ElasticsearchRepository --> ElasticsearchCrudRepository --> PagingAndSortingRepository --> CrudRepository
 */

/**
 * 继承ElasticsearchRepository里面封装很多操作ES的方法
 *
 * @author wangxl
 * @date 2019-08-12
 */
//不需要加@Component，直接可以@Autowared
public interface AtableRepository extends ElasticsearchRepository<ATable, String> {

      //注意这里的bookName和WordCount要在book实体类要有, 否则会报错
//    Page<Book> findByBookNameOr

//    Page<Book> findByBookNameOrWordCount(String bookName, Integer wordCount, Pageable pageable);
//    Page<Book> findByBookName(String bookName, Pageable pageable);

}
