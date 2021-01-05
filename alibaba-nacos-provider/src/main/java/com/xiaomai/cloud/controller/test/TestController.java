package com.xiaomai.cloud.controller.test;

import com.alibaba.fastjson.JSONObject;
import com.xiaomai.cloud.po.postgres.Book;
import com.xiaomai.cloud.service.PostgresqlService;
import com.xiaomai.cloud.service.postgres.BookServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wangfeng
 * @date 2020/11/2
 */
@RestController
@RefreshScope
@Api(value = "test", tags = "测试接口模块")
public class TestController {

    @Autowired
    private PostgresqlService postgresqlService;

    @Autowired
    private BookServiceImpl bookServiceImpl;


    @GetMapping("/get")
    public String get(){
        return "Spring Cloud Alibaba Provider 我来啦..."+ new Date();
    }

    @GetMapping("/one")
    public String demo(){
        return "demo";
    }

    @GetMapping("/demo/one")
    public String demoone(){
        return "demo-one";
    }

    @GetMapping("/time/out")
    public String requestTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(6);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Test Request Time Out----OK";
    }

    /**
     *
     * @return
     */
    @ApiOperation(value = "查询书籍列表", nickname = "书籍列表")
    @GetMapping("/postgres/getBook")
    public String getBook(){
        return JSONObject.toJSONString(postgresqlService.selectAllBook());
    }


    /**
     *
     * @param pageNum
     * @param sizeNum
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",required = true),
            @ApiImplicitParam(name = "size",value = "条数",required = true)
    })
    @ApiOperation(value = "分页查询书籍",nickname = "分页查询书籍")
    @GetMapping("/postgres/getBookPage")
    public List<Book> getBook(@RequestParam("page") Integer pageNum,
                              @RequestParam("size") Integer sizeNum){
        return bookServiceImpl.selectAllBook(pageNum,sizeNum);
    }


    /**
     *
     * @param pageNum
     * @param sizeNum
     * @param name
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",required = true),
            @ApiImplicitParam(name = "size",value = "条数",required = true),
            @ApiImplicitParam(name = "name",value = "姓名",required = true)
    })
    @ApiOperation(value = "名称分页查询书籍",nickname = "分页查询书籍")
    @GetMapping("/postgres/getBookPageByName")
    public String getBook(@RequestParam("page") Integer pageNum,
                          @RequestParam("size") Integer sizeNum,
                          @RequestParam("name") String name){
        return JSONObject.toJSONString(bookServiceImpl.selectBookPageByName(pageNum,sizeNum,name));
    }


    /**
     *
     * @param pageNum
     * @param sizeNum
     * @param code
     * @param name
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",required = true),
            @ApiImplicitParam(name = "size",value = "条数",required = true),
            @ApiImplicitParam(name = "code",value = "编号",required = true),
            @ApiImplicitParam(name = "name",value = "姓名",required = true)
    })
    @ApiOperation(httpMethod="GET",value = "编号、名称分页查询书籍",nickname = "分页查询书籍")
    @GetMapping("/postgres/getBookPageByCodeORName")
    public String getBook(@RequestParam("page") Integer pageNum,
                          @RequestParam("size") Integer sizeNum,
                          @RequestParam("code") String code,
                          @RequestParam("name") String name){
        return JSONObject.toJSONString(bookServiceImpl.selectBookPageByCodeORName(pageNum,sizeNum,code,name));
    }
}

