package org.luckystars.weixin.transfer.view.annotations;

import java.io.Serializable;
import java.util.List;

import org.luckystars.weixin.transfer.view.NewsView;
import org.luckystars.weixin.transfer.view.NewsViewBuilder;

public class AnnotationNewsViewBuilder implements NewsViewBuilder{

	@Override
	public NewsView build(List<? extends Serializable> contents) {
		if(contents==null||contents.isEmpty()){
			//TODO
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
		
		
		return true;
	}

}
