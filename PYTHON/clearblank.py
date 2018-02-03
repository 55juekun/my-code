#作用：将某个文件中的所有空格消除（百度文库和pdf文件直接复制的文字经常就是有好多空格）
import os
import re
f=open("C:/Users/lenovo/Desktop/2.txt","r+",encoding= 'utf-8')#打开文件，将文件目录（带文件名）放入第一个""
a=f.read()#将文字读出来并放入a
#print(a)
f.close()#关闭之后再次打开，即可将原文件删除
f=open("C:/Users/lenovo/Desktop/2.txt","r+",encoding= 'utf-8')
b=a.replace(" ","")#将a中所有的空格全部消掉并放入b
b=re.findall(r'\D',b)
f.write(''.join(b))#把b中的文字重新写进f
f.close()#关闭文件。此时原始文件中存在原始文字和修改后的（即没有空格）文字
