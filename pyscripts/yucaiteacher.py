# coding: utf-8
'''
育才同步老师

'''
try:
	import mysql.connector
	db_config = {
		  'user': 'root',
		  'password': 'root',
		  'host': '127.0.0.1',
		  'database': 'yucaitests',
		  'raise_on_warnings': True,
	}

	teacher_type_code = "3022100"#教师类型名称

	cnx = mysql.connector.connect(**db_config)

	findMissedPidsQuery = "SELECT tuza_userid "+ \
				"FROM t_uc_ziguang_account z " +  \
				"WHERE tuza_loginname REGEXP '[^ -~]'=0 AND z.tuza_userid not in (SELECT UUP_PID FROM t_uc_uid_pid)"
	cursorPid = cnx.cursor()
	cursorPid.execute(findMissedPidsQuery)

	missedPids = [_pid.encode("utf8") for (_pid,) in cursorPid]
	cursorPid.close()

	addUidPidQuery = "INSERT INTO t_uc_uid_pid(UUP_ID,UUP_UID,UUP_PID,UUP_USERTYPECODE,UUP_CREATETIME,UUP_ENABLE) "+\
		"VALUES(%(UUP_ID)s,%(UUP_UID)s,%(UUP_PID)s,%(UUP_USERTYPECODE)s,%(UUP_CREATETIME)s,%(UUP_ENABLE)s)"
	for _pid in missedPids:
		print(_pid)
		_insertdata={
			'UUP_ID':_pid,
			'UUP_UID':_pid,
			'UUP_PID':_pid,
			'UUP_USERTYPECODE':teacher_type_code,
			'UUP_CREATETIME':'2014-04-24 00:00:00',
			'UUP_ENABLE':'1'
		}
		cnx.cursor().execute(addUidPidQuery,_insertdata)
		pass
	cnx.commit()
	cnx.close()
	pass
except Exception, e:
	print (e)

print("ok")

input("end")