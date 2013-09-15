/*
 * @Title: ForwardingBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: TODO
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-22 上午11:02:23
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-22                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nbcedu.function.documentflow.biz.ForwardingBiz;
import com.nbcedu.function.documentflow.model.Document;
import com.nbcedu.function.documentflow.model.Forwarding;
import com.nbcedu.function.documentflow.vo.ForwardingVO;
import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcTeacher;

/** 
 * <p>转发</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-22 上午11:02:23
 */
public class ForwardingBizImpl extends DocumentFlowBaseBizImpl implements ForwardingBiz {

	/* 
	 * @see com.nbcedu.function.documentflow.biz.ForwardingBiz#addForwarding(com.nbcedu.function.documentflow.vo.ForwardingVO)
	 */
	@Override
	public void addForwarding(ForwardingVO forwardingVo) {
		BaseClient client = new BaseClient();
		Forwarding forwarding = new Forwarding();
		String[] receiversArray = forwardingVo.getReceivers();
		List<String> receivers = Arrays.asList(receiversArray);
		//排重处理
		Set<String> receiverSet = new HashSet<String>();
		receiverSet.addAll(receivers);
		List<String> receiversList = new ArrayList<String>();
		for (String receiver : receiverSet) {
			NbcUcTeacher teacher = client.queryTeacher(1, receiver);
			//避免在进行选择时，将组织结构树中的组织节点选中，进行人员校验
			if (teacher != null) {
				receiversList.add(receiver);
			}
		}
		
		Document document = (Document) getHibernateDao().get(Document.class, forwardingVo.getDocumentId());
		
		forwarding.setDocument(document);
		forwarding.setForwardingUser(forwardingVo.getForwardingUser());
		forwarding.setReceivers(receiversList);
		
		//add by qinyuan
		Set<Forwarding> forwards = document.getForwardings();
		if(forwards==null){
			forwards = new HashSet<Forwarding>();
		}
		forwards.add(forwarding);
		document.setStatus(Document.Status.FLOWING.getNum());
		document.setForwardings(forwards);
		getHibernateDao().update(document);
		getHibernateDao().create(forwarding);
	}
}
