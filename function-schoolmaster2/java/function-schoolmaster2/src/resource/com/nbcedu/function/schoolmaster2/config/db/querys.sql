
--------查询单个人
EXPLAIN 

SELECT 
	sub.id,
	sub.title,
	sub.createrId,
	sub.createrName,
	subtype.name,
	subtype.id

FROM t_sm2_subject sub,
	(
		SELECT id,name
		FROM t_sm2_type	
	) subtype,
	(
		SELECT sub_id,flag
		FROM t_sm2_subject_master
		WHERE user_uid = '1'
		AND flag = 0
	) submaster

WHERE
	sub.id = submaster.sub_id
	AND (DATE(sub.createTime)='2013-10-25' OR DATE(sub.lastUpdateTime)='2013-10-25')
	AND (sub.createTime > '2012-10-10' OR sub.lastUpdateTime > '2012-10-10') 
	AND sub.typeId = subtype.id
	AND sub.typeId in ('1','2','3','4','5')
	AND sub.createrId = 'J201108251334440044953'

ORDER BY 
	sub.createTime DESC ,
	sub.lastUpdateTime DESC
;


----查询多个


SELECT A.*
FROM 
	(
		SELECT 
			sub.id as subId,
			sub.title as subTitle,
			sub.createrId as createrId,
			sub.createrName as createrName,
			subtype.name as typeName,
			subtype.id as typeId

		FROM t_sm2_subject sub,
			(
				SELECT id,name
				FROM t_sm2_type	
			) subtype,
			(
				SELECT sub_id
				FROM t_sm2_subject_master
				WHERE user_uid = '1'
			) submaster

		WHERE
			sub.id = submaster.sub_id
			AND sub.status = 'new'
			AND (DATE(sub.createTime)='2013-10-25' OR DATE(sub.lastUpdateTime)='2013-10-25')
			AND (sub.createTime > '2012-10-10' OR sub.lastUpdateTime > '2012-10-10') 
			AND sub.typeId = subtype.id
			AND sub.typeId in ('1','2','3','4','5')
			AND sub.createrId = 'J201108251334440044953'

		ORDER BY 
			sub.createTime DESC ,
			sub.lastUpdateTime DESC
		LIMIT 0,5
	) A


UNION ALL

SELECT B.*

FROM 
	(
		SELECT 
			sub.id as subId,
			sub.title as subTitle,
			sub.createrId as createrId,
			sub.createrName as createrName,
			subtype.name as typeName,
			subtype.id as typeId

		FROM t_sm2_subject sub,
			(
				SELECT id,name
				FROM t_sm2_type	
			) subtype,
			(
				SELECT sub_id
				FROM t_sm2_subject_master
				WHERE user_uid = '1'
			) submaster

		WHERE
			sub.id = submaster.sub_id
			AND sub.status = 'updated'
			AND (DATE(sub.createTime)='2013-10-25' OR DATE(sub.lastUpdateTime)='2013-10-25')
			AND (sub.createTime > '2012-10-10' OR sub.lastUpdateTime > '2012-10-10') 
			AND sub.typeId = subtype.id
			AND sub.typeId in ('1','2','3','4','5')
			AND sub.createrId = 'J201108251334440044953'

		ORDER BY 
			sub.createTime DESC ,
			sub.lastUpdateTime DESC
		LIMIT 0,5

	) B
;






-----------------------------年度重心数量查询
SELECT ty.id,ty.name,su.cou
FROM t_sm2_type ty LEFT JOIN
	(
		SELECT  COUNT(sub.typeId) as cou,sub.typeId as tyId
		FROM 
			t_sm2_subject sub,
			(
				SELECT sub_id
				FROM t_sm2_subject_master
				WHERE 
					user_uid = '1'
					AND flag in (0,2)
			) ma
		WHERE 
			sub.id = ma.sub_id
			AND sub.moduleId = 'nianduzhongxin'
		GROUP BY sub.typeId
	) su
	ON su.tyId = ty.id




