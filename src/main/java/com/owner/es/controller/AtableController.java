package com.owner.es.controller;

import com.owner.es.model.ATable;
import com.owner.es.model.Msg;
import com.owner.es.repository.AtableRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * 内存配置
 * https://blog.csdn.net/napoay/article/details/52002180
 * 关于 update_time  del_flag 还有主键, 可以实现 实时的同步
 * https://blog.csdn.net/yiyiholic/article/details/82352236
 *
 * spring-data-elasticsearch
 *
 * restfulAPI的优点
 * 不用费力的拼 getATable?id=1&name=2  使用PathVariable简单了很多 /ATable/1001  1001直接作为请求参数  get请求就是查询
 *
 * @PathVariable可以用来映射URL中的占位符{xxx}到目标方法的参数中  带占位符的 URL 是 Spring3.0 新增的功能
 *
 * restful风格传入多个参数:
 * @GetMapping("/{id}/{name}")
 * 方法上是不区分先后顺序的 (@PathVariable("id") String id,@PathVariable("name") String name)
 * post请求封装为一个json的对象
 *
 * @author wangxl
 * @date 2019-08-12
 */
@RestController
@RequestMapping(value="/ATable")
public class AtableController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired

    private AtableRepository aTableRepository;


    /**
     * 新增注意事项, 每次新增时候,将id修改下(或者不指定id,es自动生成),
     * 若是忘记修改id,insert上一条的id,和当前条id相同,就会将上一条的记录覆盖了
     *
     * localhost:8888/ATable   参数在body里的json
     *
     * 新增 如果不传id,elasticsearch会自动创建id
     */
    // 演示：入参使用json格式的对象（使用@RequestBody注解，paramType可省略，name名与对象名保持一致）
    @ApiOperation(value = "新增一本书")
    @ApiImplicitParam(name = "ATable",value = "ATable的实体信息",required = true,dataType = "ATable",paramType = "body")
    @PostMapping(name = "/")
    public Msg postATable(@RequestBody ATable ATable) {
        aTableRepository.save(ATable);//保存一个
        return Msg.success();
    }


    /**
     *
     * 根据id删除一条记录  RESTful风格删除时传入多个id实现  1234,1235会通过@PathVariable注解封装到ids中
     * @PathVariable("id") String[] ids  localhost/user/1234,1235
     *
     * localhost:8888/ATable/{id}  delete请求方式, 如果新增的时候,没有指定id,那么删除时, 就指定用kibana的_id来删除
     *
     * id=1删除成功后,再删除id=1的不会报错, 可以多次请求没问题
     * @param id
     * @return
     */
    @ApiOperation(value = "根据主键id删除ATable信息")
    @ApiImplicitParam(name = "id",value = "ATable的主键id",required = true,dataType = "String",paramType = "path")
    @DeleteMapping("/{id}")
    public Msg deleteATable(@PathVariable("id") String id) {
        aTableRepository.deleteById(id);
        return Msg.success();
    }


    /**
     * 查询一条记录
     *
     * localhost:8888/ATable/1
     *
     * @param id
     * @return
     */
    /*@ApiImplicitParams
    注解功能：备注入参的中英文名称，配置必读、参数形式等,如下：
    value :备注输入参数名称（中文）  name：备注输入参数名称（英文）required ：该入参是否必填
    dataType:该入参的数据类型
    paramType  ：前台接口调用时url 参数形式
    body 的形式: 即body里的json
    query 的形式：getUser?user =admin
    path的形式：getUser/user/admin*/
    // 演示：路径参数入参，以及注释换行（注释换行使用两个空格和一个\n）
    @ApiOperation(value = "根据主键id查询ATable信息")
    @ApiImplicitParam(name = "id",value = "ATable表的主键id",required = true, dataType = "String",paramType = "path")
    @GetMapping("/{id}")
    public Msg selectOne(@PathVariable("id") String id) {
        Optional<ATable> optionalATable = aTableRepository.findById(id);
        return Msg.success().add(optionalATable);
    }

    /**
     * 修改
     * localhost:8888/ATable/{id}  put请求  body中的json,id必须给传上,不传的话就是新增了
     *
     * @param ATable
     * @return
     */
    @ApiOperation(value = "修改一本书")
    @ApiImplicitParam(name = "ATable",value = "ATable的实体信息",required = true,dataType = "ATable",paramType = "body")
    @PutMapping("/{id}")
    public Msg putATable(@PathVariable("id") String id, @RequestBody ATable ATable) {
        aTableRepository.save(ATable);//id相同的保存就是修改
        return Msg.success();
    }



    /**
     * 全文检索 所有field字段(text分词了, date类型不能全文检索)
     * localhost:8888/ATable/select/余华
     * @param q
     * @return
     */
    @ApiOperation(value = "全文检索(不带分页)")
    @ApiImplicitParam(name = "q",value = "全文检索的关键字",required = false, dataType = "String",paramType = "path")
    @GetMapping("/select/{q}")
    public Msg testSearch(@PathVariable String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<ATable> searchResult = aTableRepository.search(builder);
        Iterator<ATable> iterator = searchResult.iterator();
        List<ATable> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return Msg.success().add(list);
    }


    /**
     * searchContent
     * localhost:8888/ATable/getListByPage/1/3/余华
     *
     * @param page
     * @param size
     * @param q
     * @return
     */
//    swagger 中@ApiImplicitParam()跟@ApiParam()两个注解的区别
    @ApiOperation(value = "全文检索(带分页)")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page",value = "页数(从1开始)",required = true, dataType = "String",paramType = "path"),
        @ApiImplicitParam(name = "size",value = "ATable表的主键id",required = true, dataType = "String",paramType = "path"),
        @ApiImplicitParam(name = "q",value = "全文检索的关键字",required = false, dataType = "String",paramType = "path")
    })
    @GetMapping("/getListByPage/{page}/{size}/{q}")
    public Page<ATable> getListByPage(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String q) {
//        Sort sort = new Sort(Sort.Direction.DESC, "publishDate").
//                and(new Sort(Sort.Direction.ASC, "wordCount"));//按发布时间的降序 和 价格的 升序进行排序

        /*List<Sort.Order> orders=new ArrayList<Sort.Order>();
        Sort.Order publishTime = new Sort.Order(Sort.Direction.DESC,"publishTime");
        Sort.Order price = new Sort.Order(Sort.Direction.ASC,"price");
        orders.add(publishTime);
        orders.add(price);
        Sort sort = new Sort(orders);*/

        //按name的降序排列,会报错,让你换为keyword不分词的字段进行排序
//        PageRequest pageable = PageRequest.of(page - 1, size, sort);
        PageRequest pageable = PageRequest.of(page - 1, size);//0  10
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Page<ATable> ATableList = aTableRepository.search(builder, pageable);//全文检索 所有分词的字段

//        Spring Data翻页查询总是返回Page对象，Page对象提供了以下常用的方法：
        int totalPages = ATableList.getTotalPages();//总页数
        List<ATable> content = ATableList.getContent();//所有的ATable对象
        long totalElements = ATableList.getTotalElements();//返回总记录数目
        int pageNumber = ATableList.getPageable().getPageNumber();// 0
        int pageSize = ATableList.getPageable().getPageSize();// 10
        return ATableList;
//        Sort sort1 = new Sort("name");//默认升序
//        Page<ATable> all = ATableRepository.findAll(pageable);//返回所有实体，按照指定顺序排序返回
//        Page<ATable> ATableList = ATableRepository.findByName("测试", PageRequest.of(0, 2, sort));

    }

}
