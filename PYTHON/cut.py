#作用：将某个文件中的所有空格消除（百度文库和pdf文件直接复制的文字经常就是有好多空格）
import os
import re
f=open("C:/Users/lenovo/Desktop/2.txt","r+")#打开文件，将文件目录（带文件名）放入第一个""
b=f.read()#将文字读出来并放入b
def GetMiddleStr(content,startStr,endStr):#定义一个函数，函数作用为选取content字符串中以startStr开始，以endStr结尾的字符串
    startIndex = content.index(startStr)#获取startStr在content中的首字符位置，index是获取第一次出现的位置，rindex是获取最后一次出现的位置
    if startIndex>=0:#获取startStr在content中的尾字符位置
        startIndex += len(startStr)
    endIndex = content.index(endStr)#获取endStr在content中的首字符位置
    return content[startIndex:endIndex]#返回修改后的content
add='\"\"\"'+b+'\"\"\"'
delcite=add.replace("'","")
cite=delcite.replace("\\",'')
A=cite.replace("[",'')
B=A.replace("{",'')
C=B.replace("]",'')
D=C.replace("}",'')

url=D.split(",")
#url=GetMiddleStr(cite,'https://i.pximg','jpg"')
op=[]
for x in range(0,len(url)):
    try:
        value=url[x]
        
        if (value.find('https:' and 'jpg')!=-1):
            op.append(value)
    except:
        print(x)
        break

for x in range(0,len(op)):
    zhi=op[x]
    gai=GetMiddleStr(zhi,'https','jpg')
    op[x]='https'+gai+'jpg'
print(op)
#print('https://i.pximg'+url+'jpg')
f.close()#关闭文件。此时原始文件中存在原始文字和修改后的（即没有空格）文字


#https:\/\/i.pximg.net\/c\/600x600\/img-master\/img\/2011\/10\/06\/16\/55\/08\/22208183_p0_master1200.jpg
r'''(?<=").+?\.jpg(?=")'''
