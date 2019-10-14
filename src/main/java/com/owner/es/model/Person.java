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
 * A01主表字段  及其 映射出的 11张表的字段
 *
 * @author wangxl
 */
@Data
@Document(indexName = "person", type = "person", shards = 5, replicas = 0)
public class Person implements Serializable {

    //@Id 文档主键(_id,不传参会自动生成一个) 唯一标识 又作为文档的字段  /*ID,只能是Long或者Keyword类型*/
    @Id
    @Field(type = FieldType.Keyword)
    private String A01000; //主键uuid

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A0101; //姓名

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A0117A; //民族


    @Field(type = FieldType.Keyword)
    private String A0104; //性别

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A0111A; //籍贯

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A0114A; //出生地

    @Field(type = FieldType.Keyword)
    private String A0107; //出生年月, 即生日 195810

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A0187A; //爱好

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date A0134; //参加工作时间

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A1701; //简历

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A0192A; //现工作单位及全称

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A0128; //健康状况

    @Field(type = FieldType.Keyword)
    private String A0194; //基层工作经历时间

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String photodetail; //简介

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A0165; //管理类别

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A0163; //管理状态

    //奖惩信息 菜单

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A1404A;//奖惩名称汉字

    @Field(type = FieldType.Keyword)
    private String A1407;//批准日期 逗号隔开

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A1411A;//奖惩批准机关名称

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A1414;//批准奖惩机关级别34

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A1415;//奖惩时职务层次19

    @Field(type = FieldType.Keyword)
    private String A1424;//奖惩撤销日期  逗号隔开

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A1428;//批准机关性质35

    @Field(type = FieldType.Keyword)
    private String A14X01;//处分期内

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A14PZWH;//批准文号 逗号隔开

    //年度考核
    @Field(type = FieldType.Keyword)
    private String A1521;//考核年度 逗号隔开  2015,2014,2016

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A1517;//考核结论类别对应的名称  逗号隔开   ,不进行考核,

    //家庭成员菜单

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3604A;//称谓  逗号隔开

    //家庭成员
    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3601;//姓名  逗号隔开

    @Field(type = FieldType.Keyword)
    private String A3607;//出生日期 逗号隔开

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3627;//政治面貌 逗号隔开

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3611;//工作单位及职务   逗号隔开

    //培训信息菜单

    @Field(type = FieldType.Keyword)
    private String A1107;//培训起始日期   逗号隔开


    @Field(type = FieldType.Keyword)
    private String A1111;//培训结束日期   逗号隔开

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A1131;//培训班名称   逗号隔开

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A1114;//培训主办单位   逗号隔开


    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A1121A;//培训机构名称   逗号隔开

    @Field(type = FieldType.Keyword)
    private String A1151;//出国（出境）培训标识   逗号隔开  否,否

    //住址通讯菜单
    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3701;//工作单位通讯地址

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3707A;//办公电话

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3707B;//住宅电话

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3707C;//移动电话

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3707E;//秘书电话

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3708;//电子邮箱

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3711;//家庭住址

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String A3714;//住址邮政编码

}
