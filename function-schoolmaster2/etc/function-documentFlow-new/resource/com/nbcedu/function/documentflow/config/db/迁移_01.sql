DROP TABLE IF EXISTS `t_documentflow_userrole`;
CREATE TABLE `t_documentflow_userrole` (
  `id` varchar(32) NOT NULL,
  `pid` varchar(32) NOT NULL,
  `personName` varchar(255) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
	对公文流转进行权限迁移的语句
*/
insert into t_documentflow_userrole (id,pid,personName,roleName) 
	select ur.PK_T_FOUNDATION_USER_ROLE_ID,tu.PID,tu.NAME,r.name 
	from
		t_foundation_user_role as ur,t_foundation_role as r 
		,t_foundation_user as tu
	where 
		ur.ROLEID=r.PK_T_FOUNDATION_ROLE_ID and r.name like 'DOCUMENT%'
		and tu.PK_T_FOUNDATION_USER_ID = ur.USERID;

		
/*
	对公文流转 t_documentflow_document 迁移的语句
*/
ALTER TABLE `t_documentflow_document`
ADD COLUMN `INSERTTIME`  timestamp NULL;

/*填补数据*/
UPDATE t_documentflow_document 
set INSERTTIME = 
	if(PUBLISHTIME is not null,PUBLISHTIME,NOW())	
WHERE INSERTTIME is null;
