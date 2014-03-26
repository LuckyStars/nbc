package org.luckystars.weixin.transfer.view.annotations;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.luckystars.weixin.transfer.incomemsg.WeixinMsg;
import org.luckystars.weixin.transfer.view.NewsView;
import org.luckystars.weixin.transfer.view.NewsViewBuilder;

public class AnnotationNewsViewBuilder implements NewsViewBuilder{

	@Override
	public NewsView build(List<? extends Serializable> contents,WeixinMsg incomeMsg) {
		if(contents==null||contents.isEmpty()){
			return null;
		}
		Class<?> cla = contents.get(0).getClass();

		boolean valied = valiAnnotations(cla);
		NewsView result = null;
		
		if(valied){
			result = buildView(contents,incomeMsg);
		}
		
		return result;
	}

	/**
	 * 验证是否有需要的注解
	 * @param cla
	 * @return
	 * @author xuechong
	 */
	private boolean valiAnnotations(Class<?> cla) {
		
		if(cla.getAnnotation(Item.class)==null){
			return false;
		}
		AccessibleObject[] members = getMembers(cla);
		
		boolean hasDesc =  checkExists(members, Description.class);
		boolean hasTitle = checkExists(members,Title.class);
		boolean hasUrl = checkExists(members,Url.class);
		boolean hasPicUrl = checkExists(members,PicUrl.class);
		//TODO
		return true;
	}
	
	private static AccessibleObject[] getMembers(Class<?> clazz){
		
		AccessibleObject[] fields = clazz.getDeclaredFields();
		AccessibleObject[] methods = clazz.getDeclaredMethods();
		
		int fieldLength = fields != null ? fields.length : 0;
		int methodsLength = methods != null ? methods.length : 0;
		
		if((fieldLength + methodsLength)<=0){
			return new AccessibleObject[0];
		}
		
		AccessibleObject[] members = new AccessibleObject[fieldLength + methodsLength];
		if(fieldLength>0){
			System.arraycopy(fields, 0, members, 0,fieldLength);
		}
		if(methodsLength>0){
			System.arraycopy(methods, 0, members, fieldLength,methodsLength);
		}
		return members;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean checkExists(AccessibleObject[] members,Class anno){
		if(members==null||members.length<=0){return false;}
		for (int i = 0; i < members.length; i++) {
			AccessibleObject mem = members[i];
			if(mem.getAnnotation(anno)!=null){
				return true;
			}
		}
		return false;
	}


	private NewsView buildView(List<? extends Serializable> contents,WeixinMsg incomeMsg){
		NewsView view = new NewsView();
		view.setFromUserName(incomeMsg.getToUserName());
		view.setToUserName(incomeMsg.getFromUserName());
		view.setCreateTime(String.valueOf(System.currentTimeMillis()));
		view.setItems(buildItems(contents));
		return view;
	}
	

	private List<NewsView.Item> buildItems(
			List<? extends Serializable> contents) {
		List<NewsView.Item> result = new ArrayList<NewsView.Item>(contents.size());
		
		for (Serializable con : contents) {
			NewsView.Item item = fetchValue(con);
			result.add(item);
		}
		
		return result;
	}

	private NewsView.Item fetchValue(Serializable obj) {
		
		AccessibleObject[] members = getMembers(obj.getClass());
		NewsView.Item item = new NewsView.Item();
		for (AccessibleObject mem : members) {
			mem.setAccessible(Boolean.TRUE);
			
			String desc = getAnnoValue(obj,mem,Description.class);
			if(notEmpty(desc)){
				item.setDescription(desc);
			}
			
			String picurl = getAnnoValue(obj,mem,PicUrl.class);
			if(notEmpty(picurl)){
				item.setPicUrl(picurl);
			}
			
			String tit = getAnnoValue(obj,mem,Title.class);
			if(notEmpty(tit)){
				item.setTitle(tit);
			}
			
			String url = getAnnoValue(obj,mem,Url.class);
			if(notEmpty(url)){
				item.setUrl(url);
			}
			
		}
		
		return item;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getAnnoValue(Serializable obj,AccessibleObject mem, Class anno) {
		String result = "";
		if(mem.getAnnotation(anno)!=null){
			
			if(mem instanceof Method){
				Method method = (Method)mem;
				try {
					result = method.invoke(obj).toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(mem instanceof Field){
				try {
					result = ((Field) mem).get(obj).toString();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
		}
		return result;
	}

	private boolean notEmpty(String str){
		return str!=null && !str.trim().isEmpty();
	}
}
