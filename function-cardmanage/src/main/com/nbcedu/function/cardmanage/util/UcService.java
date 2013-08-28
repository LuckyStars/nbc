package com.nbcedu.function.cardmanage.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nbcedu.function.cardmanage.vo.ClassStudent;
import com.nbcedu.function.cardmanage.vo.GradeClass;
import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcClass;
import com.nbcedu.integration.uc.client.vo.NbcUcDiction;
import com.nbcedu.integration.uc.client.vo.NbcUcTreeNode;

/**
 * 用户中心的查询
 * @author xuechong
 */
@SuppressWarnings("serial")
public class UcService implements Serializable{
	private static final BaseClient client = new BaseClient();


	public static String getPersonJson(final Collection<String> checkedUids){
		
		class CloumnTree {
			
			StringBuilder jsonString = new StringBuilder();
			
			public String getCloumnTreeXmlString() {
				jsonString.append("[");
				List<NbcUcTreeNode> list = getTreeNode("root");
				for (NbcUcTreeNode nbcUcTreeNode : list) {
					disk_NbcUcTreeNode(nbcUcTreeNode);
				}
				jsonString.append("]");
				return jsonString.toString();
			}

			private void disk_NbcUcTreeNode(NbcUcTreeNode treeNode) {
				List<NbcUcTreeNode> list = getTreeNode(treeNode.getId());
				jsonString.append("{");
				jsonString.append("\"id\":");
				jsonString.append("\"");
				jsonString.append(treeNode.getId().replace("u|", ""));
				jsonString.append("\"");
				jsonString.append(",\"text\":");
				jsonString.append("\"");
				jsonString.append(treeNode.getTitle());
				jsonString.append("\"");
				if(Checked(treeNode.getId().replace("u|", ""))){
					jsonString.append(",\"checked\":true");
				}
				if(list!=null && list.size()>0){
					jsonString.append(",\"children\":[");
					for (NbcUcTreeNode tNode : list) {
						disk_NbcUcTreeNode(tNode);
					}
					jsonString.append("]");
				}
				jsonString.append("},");
			}

			List<NbcUcTreeNode> getTreeNode(String pid) {
				List<NbcUcTreeNode> list = client.queryDepartTree(pid, 3);
				return list;
			}
			
			boolean Checked(String id){
				return checkedUids!=null
					&&!checkedUids.isEmpty()
					&& checkedUids.contains(id);
			}
			
		}
		
		String userJson = new CloumnTree().getCloumnTreeXmlString();
		if(userJson!=null){
			userJson=userJson.replaceAll(",]","]");
		}
		return userJson;
		
	}
	
	
	/**
	 * 教师组织机构树
	 * @return
	 * @author xuechong
	 */
	public static String getPersonJsonString() {
		return getPersonJson(null);
	}
	
	
	public static String findUserNameByUid(String uid){
		return client.queryPerson(1, uid).getName();
	}
	/**
	 * 所有班级年级
	 * @author 黎青春
	 * @return
	 */
	public static List<GradeClass> findAllGradeClass(){
		List<GradeClass> gradeClasseList = new ArrayList<GradeClass>();
		List<NbcUcTreeNode> nbcUcTreeNodeList = client.queryTreeGradeClassStudents("root");
		for(NbcUcTreeNode nbcUcTreeNode : nbcUcTreeNodeList){	//年级
			if(nbcUcTreeNode.getId()!=null && !"".equals(nbcUcTreeNode.getId())){
				GradeClass gradeClass = new GradeClass();
				String id=nbcUcTreeNode.getId().replaceAll("gc\\|", "");
				gradeClass.setId(id);
				gradeClass.setName(nbcUcTreeNode.getTitle());
				List<NbcUcTreeNode> subNbcUcTreeNodeList = client.queryTreeGradeClassStudents(nbcUcTreeNode.getId());
				List<GradeClass> subgcList = new ArrayList<GradeClass>();
				for (NbcUcTreeNode ntn : subNbcUcTreeNodeList) {	//班级
					if(ntn.getId()!=null && !"".equals(ntn.getId())){
						GradeClass subgc = new GradeClass();
						String  subid = ntn.getId();
						subgc.setId(subid);
						subgc.setName(gradeClass.getName()+ntn.getTitle());
						subgcList.add(subgc);
					}
				}
				gradeClass.setGradeClassList(subgcList);
				gradeClasseList.add(gradeClass);
			}
		}
		return gradeClasseList;
	}
	public static String findClassByID(String classID){
		Map<String , String> param = new HashMap<String , String>();
		param.put("classId", classID);
		NbcUcClass  nbcUcClass = client.queryClass(1,param);
		StringBuffer s = new StringBuffer();
		s.append(nbcUcClass.getGradeNum()).append("年级").append(nbcUcClass.getClassName());
		return s.toString();//s.toString();
	}
	public static List<ClassStudent> findAllClassStudent(String classID){
		List<ClassStudent> classeStudentList = new ArrayList<ClassStudent>();
		Map<String , String> param = new HashMap<String , String>();
		param.put("classId", classID);
		NbcUcClass  nbcUcClass = client.queryClass(1,param);
			if(nbcUcClass.getId()!=null && !"".equals(nbcUcClass.getId())){
				ClassStudent classStudent = new ClassStudent();
				String id=nbcUcClass.getId();
				classStudent.setId(id);
				classStudent.setName(nbcUcClass.getGradeNum()+"年级"+nbcUcClass.getClassName());
				
				List<NbcUcTreeNode> subNbcUcTreeNodeList = client.queryTreeGradeClassStudents("cc|"+classID);
				List<ClassStudent> subgcList = new ArrayList<ClassStudent>();
				for (NbcUcTreeNode ntn : subNbcUcTreeNodeList) {	//班级
					if(ntn.getId()!=null && !"".equals(ntn.getId())){
						ClassStudent subgc = new ClassStudent();
						String  subid = ntn.getId();
						subgc.setId(subid);
						subgc.setName(ntn.getTitle());
						subgcList.add(subgc);
					}
				}
				classStudent.setClassStudentList(subgcList);
				classeStudentList.add(classStudent);
			}
		return classeStudentList;
	}
	public static Boolean isClassMaster(String uid){
		for (NbcUcDiction diction : client.queryIdentity(uid, 2)) {
			if(String.valueOf(diction.getId()).equals("3022102")){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	public static GradeClass findClassByUid(String uid){
		Map<String , String> param = new HashMap<String , String>();
		String pid = findPidByUid(uid);
		param.put("teacherId", pid);
		param.put("typeCode","3022102");
		NbcUcClass  nbcUcClass = client.queryClass(2,param);
		GradeClass c = new GradeClass();
		c.setId(nbcUcClass.getId());
		StringBuffer s = new StringBuffer();
		s.append(nbcUcClass.getGradeNum()).append("年级").append(nbcUcClass.getClassName());
		c.setName(s.toString());
	
		return c;
	}
	public static String findPidByUid(String uid) {
		return client.queryUidPid(1, uid);
	}
}
