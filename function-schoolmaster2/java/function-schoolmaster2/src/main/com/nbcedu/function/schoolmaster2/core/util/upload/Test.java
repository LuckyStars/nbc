package com.nbcedu.function.schoolmaster2.core.util.upload;

public class Test {

	/**
	 * 测试
	 * 
	 * @author qinyuan
	 * @since 2011-5-11
	 */
	public static void main(String[] args) {
		
		/*UpFileFormEntity formEntity = CommonsFileUploadUtil.getUploadFile(request);

		if (formEntity == null) {
			out.println("fileItem is null");
			return;
		}
		Map<String, String> paraMap = formEntity.getParaMap();
		List<FileItem> fileItems = formEntity.getFileItems();

		if (fileItems.size() == 1) {
			int loginUserId = Util.getLoginId(request);
			FileItem fItem = fileItems.get(0);

			// 得到新的文件名
			String newFileName = UserResourcePathUtil.getNewName(fItem.getName());
			File targetFile = new File(UserResourcePathUtil.getSavePath(loginUserId, newFileName));
			if (!targetFile.getParentFile().exists()) {
				targetFile.getParentFile().mkdirs();
			}

			// 写入到磁盘
			fItem.write(targetFile);

			// 要想让所写的 api 获得上传的资源的一些信息，需要手动注入到paraMap中，然后到API中获取
			paraMap.put("logo", loginUserId + "/" + newFileName);
		}*/
		
		
		
	}

}
