package com.owner.es.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author wangxl
 */
@Document(indexName = "a_index", type = "a_table", shards = 5, replicas = 0)
@Data
public class ATable implements Serializable {

    //@Id 文档主键(_id,不传参会自动生成一个) 唯一标识 又作为文档的字段  /*ID,只能是Long或者Keyword类型*/
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    //如果不能给索引库设置 默认的分词器  analyzer  searchAnalyzer最好设置为一致的
//    @Field(index=true,analyzer="ik_smart",store=true,searchAnalyzer="ik_smart",type = FieldType.text)
    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String name;



    /** 如果想全文检索,可以设置为string类型 参考这个博客 https://blog.csdn.net/zheng45/article/details/95486737 */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;




}
