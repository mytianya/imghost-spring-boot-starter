package vip.codehome.imghost.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONUtil {
	public static final Logger log=LoggerFactory.getLogger(JSONUtil.class);
	public static final ObjectMapper mapper=new ObjectMapper();
	public static <T> T toObject(String json,TypeReference<T> ref) {
		try {
			return mapper.readValue(json, ref);
		} catch (Exception e) {
			log.error("反序列化失败{},{}",json,e.toString());
			throw new RuntimeException("反序列化失败",e);
		}
	}
	public static String toJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error("序列化失败{},{}",obj,e.toString());
			throw new RuntimeException("序列化失败",e);
		}
	}
}
