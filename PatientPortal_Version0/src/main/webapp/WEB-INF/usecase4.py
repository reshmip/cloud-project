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
abc = db.cursor()
# Use all the SQL you like
cur.execute("SELECT id,userName,password FROM user WHERE userName not in('admin') limit 23")

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
    print "session open for"
    print row[1]
    #webbrowser.open('http://localhost:8081/myportal/loginUser.htm?uname=%s'%row[1])
    #r = requests.get('http://localhost:8081/myportal/')
    #print r.request.headers
    
    cur.execute("UPDATE user SET logged='false' WHERE userName='%s'"%row[1])
    for i in range(0,2):
        abc.execute("select Patient_id,Gender,Age,Status,PR_Status,Tumor,Node,Node_Coded,Metastasis,Metastasis_Coded,Converted_Stage,Last_Visit from dataset where patient_id = '%s' and Last_Visit BETWEEN '03/01/15' AND '09/10/15'"%row[0])
        out = abc.fetchall()
        for o in out:
            print o[0],o[1],o[2],o[3],o[4],o[5],o[6],o[7],o[8],o[9],o[10],o[11]
    db.commit()

#s.save()
db.close()


