/*
--日期：2013年04月12日
--编写人：秦愿
--功能：公文流转迁移（一期 to 二、三期）
--操作表：
--------参数说明----------

--------返回列说明---------
-- 0 操作失败 1 操作成功

----------测试语句---------
--set @result = '';
--call proc_nbc_lib_Doucment_Migrate1(@result); -- 0 操作失败 1 操作成功
--select @result;

*/


/*---检测是否存在：proc_nbc_lib_Doucment_Migrate 存储过程---*/
drop procedure if exists proc_nbc_lib_Doucment_Migrate1;

DELIMITER //

create PROCEDURE proc_nbc_lib_Doucment_Migrate1 (
	OUT returnStr varchar(10) -- 输出结果
)
begin
declare temp_documentId varchar(32); -- 遍历时的公文ID
declare temp_AUTHORID varchar(32); -- 临时AUTHORID
declare temp_PID varchar(32); -- 临时temp_PID

declare stop int default 0; -- 终止标记 

declare cur1 CURSOR FOR SELECT PK_T_DOCUMENTFLOW_DOCUMENT_ID FROM t_documentflow_document;

DECLARE EXIT HANDLER FOR SQLEXCEPTION   set returnStr='0'; -- rollback; 
-- 声明游标的异常处理，设置一个终止标记
declare CONTINUE HANDLER FOR SQLSTATE '02000' SET stop=1; 

-- start transaction; 
set temp_documentId = null;
set temp_AUTHORID = null;
set temp_PID = NULL;

OPEN cur1;
FETCH cur1 INTO temp_documentId;
WHILE stop <> 1 DO

	-- 1、原有公文AUTHORID（t_foundation_user主键） ==》用户pid
	set temp_AUTHORID = (select AUTHORID from t_documentflow_document where PK_T_DOCUMENTFLOW_DOCUMENT_ID = temp_documentId);
	set temp_PID = (select PID from t_foundation_user where PK_T_FOUNDATION_USER_ID = temp_AUTHORID);

	update t_documentflow_document set AUTHORID = temp_PID where PK_T_DOCUMENTFLOW_DOCUMENT_ID = temp_documentId;
	
	-- 获取下一行数据。
    FETCH cur1 INTO temp_documentId;
END WHILE;
CLOSE cur1;


set returnStr='1';
-- commit;

end;//
DELIMITER ;
