package com.owner.es;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@MapperScan("com.owner.es.dao")
@EnableElasticsearchRepositories(basePackages = "com.owner.es.repository")
public class EsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsApplication.class, args);
	}

}
