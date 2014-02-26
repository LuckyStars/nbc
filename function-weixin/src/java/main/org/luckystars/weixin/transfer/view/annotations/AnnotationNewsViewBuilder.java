package org.luckystars.weixin.transfer.view.annotations;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.util.List;

import org.luckystars.weixin.transfer.view.NewsView;
import org.luckystars.weixin.transfer.view.NewsViewBuilder;

public class AnnotationNewsViewBuilder implements NewsViewBuilder{

	@Override
	public NewsView build(List<? extends Serializable> contents) {
		if(contents==null||contents.isEmpty()){
			//TODO 没有数据的时候- -!!!该怎么办?
		}
		Class<?> cla = contents.get(0).getClass();

		valiAnnotations(cla);
		
		
		
		return null;
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
		
		
		return true;
	}
	
	private static AccessibleObject[] getMembers(Class<?> clazz){
		
		AccessibleObject[] fields = clazz.getDeclaredFields();
		AccessibleObject[] methods = clazz.getDeclaredMethods();
		
		int fieldLength = fields!=null?fields.length :0;
		int methodsLength = methods!=null?methods.length :0;
		
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
	
	@SuppressWarnings("unchecked")
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

}
