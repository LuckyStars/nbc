# coding: utf-8

'''
所有场馆和所有级别关联
'''
try:

	_config = {
		  'user': 'root',
		  'password': 'nbcroot',
		  'host': '',
		  'database': 'nbc_lib',
		  'raise_on_warnings': True,
		}
	import mysql.connector

	conn = mysql.connector.connect(**_config)

	siteQuery = "SELECT PK_SITE_ID FROM t_booksite_site"
	siteCursor = conn.cursor()
	siteCursor.execute(siteQuery)
	siteIds = [_id.encode("utf-8") for (_id,) in siteCursor]
	siteCursor.close()

	lvQuery = "SELECT PK_activityLevel_ID FROM t_booksite_activitylevel"
	lvCursor = conn.cursor()
	lvCursor.execute(lvQuery)
	lvIds = [_id.encode("utf-8") for (_id,) in lvCursor]
	lvCursor.close()


	insertQuery = "INSERT INTO t_booksite_site_level(PK_T_BOOKSITE_SITE_LEVEL_ID,LEVEL_NO,SITE_NO) "\
		+ "VALUES(%(id)s,%(lvId)s,%(siteId)s)"

	for _siteId in siteIds:
		
		_index = 0;
		
		for _lvId in lvIds:
			idSuf = _index > 9 and str(_index) or "0" + str(_index)
			_insertData = {
				'id' :_siteId[:-2] + idSuf,
				'lvId' : _lvId,
				'siteId':_siteId
			}
			print(_insertData)
			_index = _index + 1

			conn.cursor().execute(insertQuery,_insertData)
			pass

		pass


	pass
	conn.commit()
	conn.close()

except Exception, e:
	print(e)

print("ok")

input("end")