package com.nbcedu.function.cardmanage.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nbcedu.function.cardmanage.vo.ClassStudent;
import com.nbcedu.function.cardmanage.vo.GradeClass;
import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcClass;
import com.nbcedu.integration.uc.client.vo.NbcUcTreeNode;

/**
 * 用户中心的查询
 * @author xuechong
 */
public class UcService {
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
		return "ss";//client.queryPerson(1, uid).getName();
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
//		Map<String , String> param = new HashMap<String , String>();
//		param.put("classId", classID);
//		NbcUcClass  nbcUcClass = client.queryClass(1,param);
//		StringBuffer s = new StringBuffer();
//		s.append(nbcUcClass.getGradeNum()).append("年级").append(nbcUcClass.getClassName());
		return "sssss";//s.toString();
	}
	public static List<ClassStudent> findAllClassStudent(String classID){
		List<ClassStudent> classeStudentList = new ArrayList<ClassStudent>();
		Map<String , String> param = new HashMap<String , String>();
		param.put("classId", classID.replaceAll("cc\\|", ""));
		NbcUcClass  nbcUcClass = client.queryClass(1,param);
			if(nbcUcClass.getId()!=null && !"".equals(nbcUcClass.getId())){
				ClassStudent classStudent = new ClassStudent();
				String id=nbcUcClass.getId().replaceAll("gc\\|", "");
				classStudent.setId(id);
				classStudent.setName(nbcUcClass.getGradeNum()+"年级"+nbcUcClass.getClassName());
				
				List<NbcUcTreeNode> subNbcUcTreeNodeList = client.queryTreeGradeClassStudents(classID);
				List<ClassStudent> subgcList = new ArrayList<ClassStudent>();
				for (NbcUcTreeNode ntn : subNbcUcTreeNodeList) {	//班级
					if(ntn.getId()!=null && !"".equals(ntn.getId())){
						ClassStudent subgc = new ClassStudent();
						String  subid = ntn.getId().replaceAll("cc\\|", "");
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
}
