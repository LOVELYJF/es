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
 *  es的index的settings 和 mapping 设置，是最初的第一次设置。后续即使更改，也不起作用。
 *  但是mapping中的属性名称以及属性个数如果更改了，会更新到ES中。这样会导致数据的丢失。需要注意。
 *
 *
 * 通过注解生成setting和 mapping
 * 当你修改了mapping后, 比如将某字段的 long改为int,那么需要在kibana将索引删除后再启动项目,才可以启动成功,并更新了mapping
 * 新增/删除field,不需要删除已有的索引.
 * @JsonIgnore 忽略了该字段
 * @author wangxl
 */
@Document(indexName = "book_v6", type = "book", shards = 5, replicas = 0)
@Data
public class Book implements Serializable {

    //@Id 文档主键(_id,不传参会自动生成一个) 唯一标识 又作为文档的字段  /*ID,只能是Long或者Keyword类型*/
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    //如果不能给索引库设置 默认的分词器  analyzer  searchAnalyzer最好设置为一致的
//    @Field(index=true,analyzer="ik_smart",store=true,searchAnalyzer="ik_smart",type = FieldType.text)
    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String bookName;

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String author;

    @Field(type = FieldType.Integer)
    private Integer wordCount;

    /** 如果想全文检索,可以设置为string类型 参考这个博客 https://blog.csdn.net/zheng45/article/details/95486737 */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String briefIntroduction;

    /** 根据 更新时间 增量同步数据库 */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 删除标记 */
    @Field(type = FieldType.Integer)
    private Integer delFlag;

}
