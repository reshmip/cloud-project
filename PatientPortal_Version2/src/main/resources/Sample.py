#import sys
#if "C:\bhavna_new\Fall 2016\Cloud\Final Project" not in sys.path:
#    sys.path.append("C:\bhavna_new\Fall 2016\Cloud\Final Project")
#print ("Hello World")


#!/usr/bin/python
import pymysql
import urllib, urllib2, cookielib
import requests
import webbrowser
import csv
import os
from requests import Request, Session

db = pymysql.connect(host="localhost",    # your host, usually localhost
                     user="root",         # your username
                     passwd="password",  # your password
                     db="patientportal")        # name of the data base

# you must create a Cursor object. It will let
#  you execute all the queries you need
cur = db.cursor()

# Use all the SQL you like
cur.execute("SELECT id,userName,password FROM user WHERE userName not in('admin') limit 3")

data = cur.fetchall()
# print all the first cell of all the rows
for row in data:
    #print row[1],row[2]
	login_data = urllib.urlencode({'username' : row[1], 'j_password' : row[2]})
	#data = {'username' : row[1], 'j_password' : row[2]}
	url='http://localhost:8081/myportal/'
	s = requests.Session()
	#s = Session()
	requests.post('http://localhost:8081/myportal/', login_data)
	print " session open for"
	print row[1]
	#webbrowser.open('http://localhost:8081/myportal/loginUser.htm?uname=%s'%row[1])
	#r = requests.get('http://localhost:8081/myportal/')
	#print r.request.headers
	
	cur.execute("UPDATE user SET logged='true' WHERE userName='%s'"%row[1])
	for i in range(0,2):
		cur.execute("select patient_id from dataset where patient_id = '%s' and Last_Visit BETWEEN '3/1/2015' AND '6/9/2015'"%row[0])
		records = cur.fetchall()
		for record in records;
			print record[0]
	
	dir_name = os.path.abspath("paths")
	base_filename = 'report'
	format = 'csv'
	index = str(row[0])
	#print dir_name
	
	full_path = os.path.join(dir_name,base_filename+index+'.'+format)
	print "uploaded the below file"
	print os.path.join(base_filename+index+'.'+format)
	with open(full_path, 'w') as outFile:
		fileWriter = csv.writer(outFile)
		with open('user.csv','r') as inFile:
			fileReader = csv.reader(inFile)
			for row in fileReader:
				fileWriter.writerow(row)
	db.commit()

#s.save()
db.close()


