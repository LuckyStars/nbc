# coding: utf-8
'''
Created on 2013-11-21
丢到服务器上双击就行= =
@author: xuechong
'''

#要搜索的人在这里
namList = ["张","李"]
#数据库连接配置
db_config = {
	  'user': 'root',
	  'password': 'nbc123',
	  'host': '172.16.30.183',
	  'database': 'nbc_lib_shijia',
	  'raise_on_warnings': True,
	}

import mysql.connector
import os

cnx = mysql.connector.connect(**db_config)
queryStr = "SELECT tea.NUUT_REALNAME as name ,up.UUP_UID as uid ,up.UUP_PID as pid " + \
	"FROM t_uc_user_teacher tea,t_uc_uid_pid up " + \
	"WHERE " + \
	"up.UUP_PID = tea.NUUT_ID AND " + \
	"( + "

for _name in namList:
	queryStr += "tea.NUUT_REALNAME like '%"+str(_name)+"%' OR "
queryStr = queryStr[:len(queryStr)-3]
queryStr = queryStr + ")"

cursor = cnx.cursor()
cursor.execute(queryStr)

curPath = os.getcwd()
outputText = open(curPath + os.sep + "results.txt","w") 

for (_name, _uid, _pid) in cursor:
	resultLine = "name:"+_name+"|uid:"+_uid+" |pid:"+ _pid+"\n"
	print (resultLine)
	outputText.write(resultLine.encode("utf8"))
	
outputText.close()
cursor.close()
cnx.close()
print "end"
input("input any to end")