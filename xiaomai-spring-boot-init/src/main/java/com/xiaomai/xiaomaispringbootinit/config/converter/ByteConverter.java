package com.xiaomai.xiaomaispringbootinit.config.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ByteConverter extends JsonSerializer<byte[]> {

	@Override
	public void serialize(byte[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(new String(value));
		// 默认规则转为Base64
		// g.writeBinary(provider.getConfig().getBase64Variant(),value, 0,
		// value.length);
	}
}
