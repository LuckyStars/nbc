package com.nbcedu.function.schoolmaster2.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.xwork.StringUtils;

import com.nbcedu.function.schoolmaster2.core.action.BaseAction;


@SuppressWarnings("serial")
public class MasterIndexAction extends BaseAction{

	private static volatile Map<String, String> CACHE = new HashMap<String, String>();
	private static final Timer timer = new Timer();
	
	public void findLinshi(){
		
		
	}
	
	
	private String findData(SearchFunction function){
		if(function!=null){
			String result = CACHE.get(function.getId());
			if(StringUtils.isBlank(result)){
				result = function.doSearch();
				CACHE.put(function.getId(), result);
			}
			return result;
		}
		return "";
	}
	
	
	private abstract class SearchFunction{
		/**
		 * 返回前台需要的json数据
		 * @return
		 * @author xuechong
		 */
		public abstract String search();
		
		/**
		 * 返回放在缓存中的KEY
		 * @return
		 * @author xuechong
		 */
		public abstract String getId();
		
		public final String doSearch(){
			timeToDie(this.getId());
			return this.search();
		}
	}
	
	private static void timeToDie(final String id){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				CACHE.remove(id);
			}
		}, 1000L*60L*2L);
	}
	
	///////////////////////
	////getters&setters/////
	///////////////////////
 	
}
