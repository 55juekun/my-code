#红色字体为注释，需要事先下载安装BeautifulSoup和lxml
#忘了urllib.request是不是需要自己下载的了
#所有下载的图片会放在这个代码所在的文件夹内
import urllib.request   #发出请求，得到网页反馈，但存在部分编码问题
from bs4 import BeautifulSoup   #作用同上，不存在编码问题
import time     #导入时间，防止因为请求网页速度过快而被屏蔽
import os
import re   #导入正则，用于筛选
#定义初始图片序号
os.chdir("d:/ee")
global x
x=1

def GetMiddleStr(content,startStr,endStr):
    startIndex = content.index(startStr)
    if startIndex>=0:
        startIndex += len(startStr)
    endIndex = content.index(endStr)
    return content[startIndex:endIndex]
for i in range(65,122):
    b=chr(i)
    print(b)
    file="%srizfQjm" %b
    url='http://13IG7rTUS3gbDfQjh9'+file
#   网址，随便选了个贴吧
    wb_data=urllib.request.urlopen(url)     #请求打开网页代码
    soup = BeautifulSoup(wb_data,'lxml')    #用beautifulsoup，
#   'lxml'编译打开的网页
    soupl=soup.prettify()#将代码格式化输出
    def getImg(soupl):#定义一个方法
        global x
        reg=r'src="(.*?\.jpg)"'#筛选的标准为后置jpg
        imgre=re.compile(reg)#用正则先编译这个筛选
#   防止网页代码过多编译时软件死机
        imglist=re.findall(imgre,soupl)#从soupl（网页）
#   中筛选imgre（）
        print(imglist)
        op=str(imglist)
        if len(op)<10:
            zz=imglist
        else:
            xx=GetMiddleStr(op,'&amp;src=','jpg')
            poop=xx+'jpg'
            zz=poop.split("!")
        print(zz)
        for imgurl in zz:#使用循环开始筛选所用图片
            urllib.request.urlretrieve(imgurl,"%s.jpg" % x)
#   将筛选到的图片命名为x.jpg
            print("%s.jpg" % x)
            x+=1#x自加
            time.sleep(20)#暂停程序0.2m，防止被屏蔽
    print("*******************************")
    print(getImg(soupl))#打印出所有图片
    print(i)
