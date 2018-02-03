import urllib.request
from bs4 import BeautifulSoup
import time
import os
import sys
import re
import tkinter.messagebox
from tkinter import simpledialog,messagebox
root = tkinter.Tk()
root.withdraw()
y=simpledialog.askstring('folder','图片所放的文件夹')
u="E:\\实验用\\"
z=u+y
print(z)
try:
    os.mkdir(z)
except Exception as e:
    a = messagebox.askokcancel('文件夹已经存在', '是否继续存储此文件夹')
    if(a==False):
        print("Error!!")
os.chdir(z)
defall='https://www.nvshens.com'
url=defall+simpledialog.askstring('url','第一页网址')
print("url:"+url)
default=defall
def re_pro():
    python = sys.executable
    os.execl(python, python, * sys.argv)
time.sleep(1)

global x
x=int(simpledialog.askstring('序号','照片序号'))#控制照片序号

def GetMiddleStr(content,startStr,endStr):#定义一个函数，函数作用为选取content字符串中以startStr开始，以endStr结尾的字符串
    startIndex = content.index(startStr)#获取startStr在content中的首字符位置，index是获取第一次出现的位置，rindex是获取最后一次出现的位置
    if startIndex>=0:#获取startStr在content中的尾字符位置
        startIndex += len(startStr)
        content=content[startIndex-1:]
    endIndex = content.index(endStr)#获取endStr在content中的首字符位置
    return content[1:endIndex]#返回修改后的content
def getImg(middle):
    global x
    reg=r'src="(.*?\.jpg)"'
    imgre=re.compile(reg)
    #print("midd"+middle)
    imglist=re.findall(imgre,middle)
    imgliststr=str(imglist)
    # if len(imgliststr)<10:
    #     finallist=imglist
    # else:
    #     mid=GetMiddleStr(imgliststr,'&amp;src=','jpg')
    #     print('mid::'+mid)
    #     jpgurl=mid+format
    #     finallist=jpgurl.split("!")
    finallist=imglist
    for imgurl in finallist:
        urllib.request.urlretrieve(imgurl,"%s.jpg" % x)
        print("%s.jpg" % x)
        x+=1
        time.sleep(2)
for i in range(2,50):
    wb_data=urllib.request.urlopen(url)
    soup = BeautifulSoup(wb_data,'lxml')
    soupl=soup.prettify()
    add = '\"\"\"' + soupl + '\"\"\"'
    delcite = add.replace("'", "")
    delenter = delcite.replace('\n', '')
    delblank = delenter.replace(' ', '')
    delo = delblank.replace('\"\"', '')
    #print(delo + "+++++++++++++++++++++++++")
    middle = GetMiddleStr(delo, 'id="hgallery"', '</ul')

    print("*******************************")
    print(getImg(middle))#打印出所有图片
    print("*******************************")
    print(i)#下一页的页码
    
    tab='>%s<' %i
    print("标签为："+tab)
    try:
        loc1=delblank.rindex(tab)
    except Exception as e :
        print(e)
        break
    delbehind=delblank[:loc1+1]
    print('################################')
    loc2=delbehind.rindex('href="')
    success=delbehind[loc2+1:]
    print(success)
    url=GetMiddleStr(success.replace('amp;',''),'ref="','">')
    if(len(url)<20):
        url=default+url
    print('网址为：'+url)

re_pro()


