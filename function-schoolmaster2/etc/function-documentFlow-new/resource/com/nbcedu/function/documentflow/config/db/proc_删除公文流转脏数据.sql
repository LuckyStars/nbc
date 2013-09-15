/*
--日期：2012年11月02日
--编写人：秦愿
--功能：删除府学公文流转脏数据
--操作表：
--------参数说明----------
--docId:公文ID

--------返回列说明---------
-- 0 删除失败 1 删除成功

----------测试语句---------
--set @result = '';
--call proc_nbc_lib_Doucment_del('1',@result); -- 修改这里的参数, 0--代表删除失败 1--代表删除成功
--select @result;

*/


/*---检测是否存在：proc_nbc_lib_Doucment_del存储过程---*/
drop procedure if exists proc_nbc_lib_Doucment_del;

DELIMITER //

create PROCEDURE proc_nbc_lib_Doucment_del (
	docId varchar(100),
	OUT returnStr varchar(10) -- 输出结果
)
begin 
DECLARE EXIT HANDLER FOR SQLEXCEPTION   set returnStr='0'; -- rollback; 

-- start transaction; 
-- set docId = '1';


-- 删除公文附件
delete from t_documentflow_attachment where FK_DOCUMENTFLOW_DOCUMENT_ID in (docId);

-- 删除公文评论
delete from t_documentflow_comment where FK_DOCUMENTFLOW_DOCUMENT_ID in (docId);

-- 删除公文任务
delete from t_documentflow_documenttask where DOCUMENTID in (docId);

-- 删除公文转发
delete from t_documentflow_forwarding_receiver where FK_T_DOCUMENTFLOW_FORWARDING_ID in (
	select PK_T_DOCUMENTFLOW_FORWARDING_ID from t_documentflow_forwarding where FK_DOCUMENTFLOW_DOCUMENT_ID in (docId)
);
delete from t_documentflow_forwarding where FK_DOCUMENTFLOW_DOCUMENT_ID in (docId);

-- 删除公文短信通知
delete from t_documentflow_notifier where FK_T_DOCUMENTFLOW_DOCUMENT_ID in (docId);

-- 删除已经接受公文的用户
delete from t_documentflow_received where FK_T_DOCUMENTFLOW_DOCUMENT_ID in (docId);

-- 删除接受公文的用户
delete from t_documentflow_receiver where FK_T_DOCUMENTFLOW_DOCUMENT_ID in (docId);

-- 删除公文
delete from t_documentflow_document where PK_T_DOCUMENTFLOW_DOCUMENT_ID in (docId);


/************  Quartz****************/
delete from qrtz_triggers where TRIGGER_NAME in ('expiringDocumentJobBean_' + docId);
delete from qrtz_triggers where TRIGGER_NAME in ('notifyingJobBean_' + docId);

delete from qrtz_simple_triggers where TRIGGER_NAME in ('expiringDocumentJobBean_' + docId);
delete from qrtz_simple_triggers where TRIGGER_NAME in ('notifyingJobBean_' + docId);

delete from qrtz_job_details where JOB_NAME in ('expiringDocumentJobBean_' + docId);
delete from qrtz_job_details where JOB_NAME in ('notifyingJobBean_' + docId);


/************* 删除门户 ****************/

delete from t_portal_usermessage where MESSAGE in (
	select PK_T_PORTAL_MESSAGE_ID from t_portal_message where MESSAGEID in (docId)
);

delete from t_portal_messagerelation where MESSAGE in (
	select PK_T_PORTAL_MESSAGE_ID from t_portal_message where MESSAGEID in (docId)
);

delete from t_portal_message where MESSAGEID in (docId);

set returnStr='1';
-- commit;

select returnStr;

end;//
DELIMITER ;  -- 把分割符又还原为“;”

