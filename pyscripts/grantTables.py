try:

	_config = {
		  'user': 'root',
		  'password': 'root',
		  'host': '',
		  'database': 'nbc',
		  'raise_on_warnings': True,
		}
	import mysql.connector


	conn = mysql.connector.connect(**_config)
	tableNamesCursor = conn.cursor()
	tableNamesCursor.execute("SHOW TABLES LIKE 't_uc_%' ;")

	_table_list = [tab_name.encode("utf-8") for (tab_name,) in tableNamesCursor]

	tableNamesCursor.close()

	print(_table_list)
	_db_name = "nbc"
	_user_name = "tester"

	import os

	curPath = os.getcwd()
	outputText = open(curPath + os.sep + "grantQuerys.txt","w")

	for tabName in _table_list:
		query ="GRANT SELECT ON " + _db_name + "." + tabName\
			 +" TO 'tester'@'%'" + ";\n"
		outputText.write(query.encode("utf8"))
		pass
	
	outputText.close()


	pass
except Exception, e:
	print(e)


input ("end")



