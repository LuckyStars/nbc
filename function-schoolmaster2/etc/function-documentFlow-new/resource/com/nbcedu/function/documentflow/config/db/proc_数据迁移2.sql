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
--call proc_nbc_lib_Doucment_Migrate2(@result); -- 0 操作失败 1 操作成功
--select @result;

*/


/*---检测是否存在：proc_nbc_lib_Doucment_Migrate 存储过程---*/
drop procedure if exists proc_nbc_lib_Doucment_Migrate2;

DELIMITER //

create PROCEDURE proc_nbc_lib_Doucment_Migrate2 (
	OUT returnStr varchar(10) -- 输出结果
)
begin
declare temp_routeId varchar(32); -- 遍历时的流转路径ID
declare temp_CREATORID varchar(32); -- 临时CREATORID
declare temp_PID2 varchar(32); -- 临时temp_PID2
declare stop2 int default 0; -- 终止标记 

declare cur2 CURSOR FOR SELECT PK_T_DOCUMENTFLOW_ROUTE_ID FROM t_documentflow_route;

DECLARE EXIT HANDLER FOR SQLEXCEPTION   set returnStr='0'; -- rollback; 
-- 声明游标的异常处理，设置一个终止标记
declare CONTINUE HANDLER FOR SQLSTATE '02000' SET stop2=1; 

-- start transaction; 
set temp_routeId = null;
set temp_CREATORID = null;
set temp_PID2 = null;


OPEN cur2;
FETCH cur2 INTO temp_routeId;
WHILE stop2 <> 1 DO

	-- 2、原有流转路径CREATORID（t_foundation_user主键） ==》用户pid
	set temp_CREATORID = (select CREATORID from t_documentflow_route where PK_T_DOCUMENTFLOW_ROUTE_ID = temp_routeId);
	set temp_PID2 = (select PID from t_foundation_user where PK_T_FOUNDATION_USER_ID = temp_CREATORID);

	update t_documentflow_route set CREATORID = temp_PID2 where PK_T_DOCUMENTFLOW_ROUTE_ID = temp_routeId;
	
	-- 获取下一行数据。
    FETCH cur2 INTO temp_routeId;
END WHILE;
CLOSE cur2;



set returnStr='1';
-- commit;

end;//
DELIMITER ;
