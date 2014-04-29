package org.luckystars.weixin.utils.json;

import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class GsonUtil {
	public static final Gson gson = new Gson();
	
	@SuppressWarnings("serial")
	public static Map<String, String> fromJson(String json){
		return gson.fromJson(json, 
				new TypeToken<Map<String, String>>() {}.getType());
	}
}
