package com.nbcedu.function.syllabus.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nbcedu.function.syllabus.devcore.service.impl.BaseServiceImpl;
import com.nbcedu.function.syllabus.model.SLBLesson;
import com.nbcedu.function.syllabus.service.SLBLessonService;
import com.nbcedu.function.syllabus.service.SLBUploadService;
import com.nbcedu.function.syllabus.service.UCService;
import com.nbcedu.function.syllabus.utils.Utils;
import com.nbcedu.function.syllabus.vo.CourseVo;

@SuppressWarnings("unchecked")
@Repository("slbUploadService")
public class SLBUploadServiceImpl extends BaseServiceImpl implements
		SLBUploadService {

	private static final Logger logger = Logger
			.getLogger(SLBUploadServiceImpl.class);
	@Autowired(required = true)
	private UCService ucService;
	@Autowired(required = true)
	private SLBLessonService slbLessonService;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean importLessonExl(MultipartFile file, String classId) {
		try {
			String fileName = file.getOriginalFilename();
			String fileSuffix = Utils.subFileSuffix(fileName);
			List<CourseVo> courseList = ucService.findAllCourse();
			List<SLBLesson> listLesson = slbLessonService.findBy("classId",
					classId);
			// 判断是否已经导入
			if (listLesson != null && listLesson.size() > 0) {
				slbLessonService.removeByProperty("classId", classId);
			}
			// 判断exl版本
			Workbook readWorkBook = null;

			if (fileSuffix.equals("xlsx")) {
				readWorkBook = new XSSFWorkbook(file.getInputStream());
			} else if (fileSuffix.equals("xls")) {
				readWorkBook = new HSSFWorkbook(file.getInputStream());
			}
			
			if (readWorkBook!=null) {
				Sheet readSheet = readWorkBook.getSheetAt(0);
				int rowNum = readSheet.getLastRowNum();// 获得行数
				for (int i = 2; i <= rowNum; i++) {
					Row readRow = readSheet.getRow(i);// 获得行
					int coloumNum = readRow.getPhysicalNumberOfCells();// 获得此行总列数
					String dayStr = readRow.getCell(0).getStringCellValue(); // 获得星期几
					int dayOfCycle = Utils
							.characersToNum(dayStr.toCharArray()[0]);
					for (int j = 1; j < coloumNum; j++) {
						Cell readCell = readRow.getCell(j);// 获得元素对象
						String courseName = readCell.getStringCellValue();
						SLBLesson slbLesson = new SLBLesson();
						// 获得节数
						Row readRow1 = readSheet.getRow(1);
						Cell readCell1 = readRow1.getCell(j);
						int indexOfDay = (int) readCell1.getNumericCellValue();

						slbLesson.setIndexOfDay(indexOfDay);
						slbLesson.setClassId(classId);
						slbLesson.setDayOfCycle(dayOfCycle);
						// 根据courseName查询courseId
						String courseId = getCourseIdByCourseName(courseList,
								courseName.replaceAll(" ", courseName));
						slbLesson.setCourseId(courseId);
						slbLessonService.add(slbLesson);
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return false;
		}
	}

	/**
	 * 根据课程名获得课程id
	 * 
	 * @param courseName
	 * @return
	 */
	private String getCourseIdByCourseName(List<CourseVo> courseList,
			String courseName) {
		String courseId = null;
		for (CourseVo courseVo : courseList) {
			if (courseName.equals(courseVo.getName())) {
				courseId = courseVo.getId();
			}
		}
		return courseId;
	}
}
