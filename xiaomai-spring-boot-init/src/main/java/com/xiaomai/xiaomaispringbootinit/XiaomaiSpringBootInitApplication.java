package com.xiaomai.xiaomaispringbootinit;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xiaomai.xiaomaispringbootinit.compent.Dog;
import com.xiaomai.xiaomaispringbootinit.compent.MyConfig;
import com.xiaomai.xiaomaispringbootinit.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Madison
 * @date 2021/4/7
 */
@EnableSwagger2
// 启用了重试功能
@Slf4j
@RestController
@EnableRetry
@SpringBootApplication
// @ImportResource(locations = {"classpath:application.properties"})
public class XiaomaiSpringBootInitApplication {
    @Value("${spring.application.name}")
    private String applicationname;

    @Value("${server.port}")
    private String port;

    @Autowired
    private RetryService retryService;

    @Autowired
    private Snowflake snowflake;


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(XiaomaiSpringBootInitApplication.class, args);

       /* String[] names = context.getBeanDefinitionNames();
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }

        int beanDefinitionCount = context.getBeanDefinitionCount();
        log.info("定义Bean的数量：{}",beanDefinitionCount);

        Dog dog01 = context.getBean("getDog",Dog.class);
        Dog dog02 = context.getBean("getDog",Dog.class);
        log.info("获取bean是否一致：{}",dog01==dog02);

        MyConfig bean = context.getBean(MyConfig.class);
        Dog dog1 = bean.getDog();
        Dog dog2 = bean.getDog();
        System.out.println(dog1==dog2);

        String[] beansOfType = context.getBeanNamesForType(Dog.class);*/

    }
    /*
	//Json序列化 jackson
	@Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
    	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    	//设置日期格式
    	ObjectMapper objectMapper = new ObjectMapper();
    	SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	objectMapper.setDateFormat(smt);
    	
    	// 序列换成json时,将所有的long变成string,因为js中得数字类型不能包含所有的java long值
        SimpleModule longsimpleModule = new SimpleModule("LongModule");
        longsimpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        longsimpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        
        //序列化byte为字符串（默认为base64）
        SimpleModule bytesimpleModule = new SimpleModule("ByteModule");
        bytesimpleModule.addSerializer(byte[].class, new CustomByteSerializer()); 
        objectMapper.registerModules(longsimpleModule,bytesimpleModule);
        
        // 解决jackson2无法反序列化LocalDateTime的问题
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        
        
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper); 
    	
    	//设置中文编码格式
    	List<MediaType> list = new ArrayList<MediaType>();
    	list.add(MediaType.APPLICATION_JSON_UTF8);
    	mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
    	return mappingJackson2HttpMessageConverter;
    }	
	//自定义byte类型序列化规则
    public static class CustomByteSerializer extends JsonSerializer<byte[]> {
		@Override
		public void serialize(byte[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			gen.writeString(new String(value));	
			//默认规则转为Base64  g.writeBinary(provider.getConfig().getBase64Variant(),value, 0, value.length);
		}
    }*/
    
    

    @GetMapping("/retryservice")
    private String retryServiceTest(){
        retryService.retry();
        return "OK";
    }

    @RequestMapping("/test")
    public String Test(){
        return "时间:["+new Date()+"]应用名称:["+applicationname+"]端口号:["+port+"]";
    }

    @GetMapping("/")
    public String getIndex() {
        // 级别由低到高 trace<debug<info<warn<error
        log.trace("这是一个trace日志...");
        log.debug("这是一个debug日志...");

        // SpringBoot默认是info级别，只会输出info及以上级别的日志
        log.info("这是一个info日志...");
        log.warn("这是一个warn日志...");
        log.error("这是一个error日志...");
        String str = "https://www.baidu.com";
        log.info("======欢迎访问百度：{}\n", str);

        log.info(snowflake.toString());
        log.info("雪花算法：{}",snowflake.nextIdStr());
        return "OK";
    }

//    @Bean
//    public Snowflake getSnowflake(){
//        return IdUtil.createSnowflake(1,1);
//    }
}
