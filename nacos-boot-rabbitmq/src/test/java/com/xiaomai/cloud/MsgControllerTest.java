package com.xiaomai.cloud;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Madison
 * @date 2021/2/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private RestTemplate template;


    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
        //User user =new User("root","root");
        // session.setAttribute("user",user); //拦截器那边会判断用户是否登录，所以这里注入一个用户
    }

    /**
     * mockMvc.perform   执行一个请求
     * MockMvcRequestBuilders.get(“/user/1”)   构造一个请求，Post请求就用.post方法
     * contentType(MediaType.APPLICATION_JSON_UTF8)  代表发送端发送的数据格式是application/json;charset=UTF-8
     * accept(MediaType.APPLICATION_JSON_UTF8)  代表客户端希望接受的数据类型为application/json;charset=UTF-8
     * session(session)   注入一个session，这样拦截器才可以通过

     * ResultActions.andExpect   添加执行完成后的断言
     * ResultActions.andExpect(MockMvcResultMatchers.status().isOk())   方法看请求的状态响应码是否为200如果不是则抛异常，测试不通过
     * andExpect(MockMvcResultMatchers.jsonPath(“$.author”).value(“嘟嘟MD独立博客”))  这里jsonPath用来获取author字段比对是否为嘟嘟MD独立博客,不是就测试不通过
     * ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息
     */
    @Test
    public void addLearn() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/send?msg=123456")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
