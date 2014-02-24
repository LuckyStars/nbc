package org.luckystars.weixin.transfer.view.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * &lt;Description&gt;&lt;![CDATA[description1]]&gt;&lt;/Description&gt;
 * <br>
 * Description	 图文消息描述<br>
 * @author xuechong
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD,ElementType.METHOD})
public @interface Description  {

}
