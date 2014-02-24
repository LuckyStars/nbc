package org.luckystars.weixin.transfer.view.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.luckystars.weixin.transfer.view.NewsViewBuilder;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface Item {
	
	Class<? extends NewsViewBuilder> builder = AnnotationNewsViewBuilder.class;
	
}
