package com.nbcedu.function.teachersignup.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;

import com.nbcedu.function.teachersignup.core.util.exl.annotations.ExlData;
import com.nbcedu.function.teachersignup.core.util.exl.annotations.ExlModel;
import com.nbcedu.function.teachersignup.model.TSReward;
import com.nbcedu.function.teachersignup.model.TSSign;
import com.nbcedu.function.teachersignup.model.TSSubject;
@ExlModel
public class TSSignVo {
	/*** 报名ID*/
	private String signId;
	/*** 教师姓名*/
	@ExlData(sortId=1,title="教师姓名")
	private String teaName;
	/*** 报名类型(中文)*/
	@ExlData(sortId=2,title="报名类型")
	private String signSubject;
	/*** 获奖类型*/
	@ExlData(sortId=3,title="获奖情况")
	private String rewardName;
	/**
	 * 奖项ID 
	 */
	private String rewId;
	
	public static class Factory{
		@SuppressWarnings("serial")
		public static List<TSSignVo> build(
										List<TSSign> signList,
										List<TSSubject> subList,
										List<TSReward> rewList){
			
			Map<String,String> subMap = new HashMap<String, String>(subList.size());
			for (TSSubject sub : subList) {
				subMap.put(sub.getId(), sub.getName());
			}
			
			Map<String,String> rewMap = null;
			if(org.springframework.util.CollectionUtils.isEmpty(rewList)){
				rewMap = new HashMap<String, String>(){{
					put("None", "暂无获奖情况");
				}};
			}else{
				rewMap = new HashMap<String, String>(rewList.size()+1){{
					put("None", "暂无获奖情况");
				}};
				for (TSReward rew : rewList) {
					rewMap.put(rew.getId(), rew.getName());
				}
			}
			
			List<TSSignVo> result = new ArrayList<TSSignVo>();
			if(signList!=null&&!signList.isEmpty()){
				for (TSSign tSign : signList) {
					TSSignVo vo = new TSSignVo();
					vo.setSignId(tSign.getId());
					vo.setTeaName(tSign.getUserName());
					vo.setSignSubject(subMap.get(tSign.getSubjectId()));
					String rewId = tSign.getRewardId();
					vo.setRewardName(StringUtils.isNotBlank(rewId)?
							rewMap.get(rewId):rewMap.get("None")
					);
					vo.setRewId(tSign.getRewardId());
					result.add(vo);
				}
			}
			return result;
		}
	}
	
	
	//////////////////////////
	/////getters&setters//////
	//////////////////////////
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getSignSubject() {
		return signSubject;
	}
	public void setSignSubject(String signSubject) {
		this.signSubject = signSubject;
	}
	public String getRewardName() {
		return rewardName;
	}
	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}
	public String getRewId() {
		return rewId;
	}
	public void setRewId(String rewId) {
		this.rewId = rewId;
	}
	
}
