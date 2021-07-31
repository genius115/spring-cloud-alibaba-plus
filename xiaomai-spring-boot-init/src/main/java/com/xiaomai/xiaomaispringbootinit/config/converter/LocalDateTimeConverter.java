package com.xiaomai.xiaomaispringbootinit.config.converter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * @ClassName:  LocalDateTimeConverter   
 * @Description:	  
 * @author: wangfeng
 * @date:   2021年6月26日 下午4:59:53   
 *     
 * @Copyright: 2021 Inc. All rights reserved.
 */
public class LocalDateTimeConverter extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
}