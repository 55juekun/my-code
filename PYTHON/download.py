#红色字体为注释，需要事先下载安装BeautifulSoup和lxml
#忘了urllib.request是不是需要自己下载的了
#所有下载的图片会放在这个代码所在的文件夹内
import urllib.request   #发出请求，得到网页反馈，但存在部分编码问题
from bs4 import BeautifulSoup   #作用同上，不存在编码问题
import time     #导入时间，防止因为请求网页速度过快而被屏蔽
import os
import re   #导入正则，用于筛选
#定义初始图片序号
os.chdir("e://实验用")
global x
x=70
def GetMiddleStr(content,startStr,endStr):#定义一个函数，函数作用为选取content字符串中以startStr开始，以endStr结尾的字符串
    startIndex = content.index(startStr)#获取startStr在content中的首字符位置，index是获取第一次出现的位置，rindex是获取最后一次出现的位置
    if startIndex>=0:#获取startStr在content中的尾字符位置
        startIndex += len(startStr)
    endIndex = content.index(endStr)#获取endStr在content中的首字符位置
    return content[startIndex:endIndex]#返回修改后的content
for i in range(2,50):
    file="%s.html" %i
    url='http://www.soutaotu.com/guoneitaotu/13306_'+file
    print(url)
#   网址，随便选了个贴吧
   # headers = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0'}
    request = urllib.request.Request(url)
    wb_data=urllib.request.urlopen(request)     #请求打开网页代码
    soup = BeautifulSoup(wb_data,'lxml')    #用beautifulsoup，
#   'lxml'编译打开的网页
    soupl=soup.prettify()#将代码格式化输出
    #print(soupl)
    def getImg(soupl):#定义一个方法
        global x
        add='\"\"\"'+soupl+'\"\"\"'
        delcite=add.replace("'","")
        cite=delcite.replace("\\",'')
        reg=r'src="(.*?\).jpg)"'#筛选的标准为后置jpg
        imgre=re.compile(reg)#用正则先编译这个筛选
#   防止网页代码过多编译时软件死机
        imglist=re.findall(imgre,cite)#从soupl（网页）
        print(imglist)
#   中筛选imgre（）
        for imgurl in imglist:#使用循环开始筛选所用图片
            urllib.request.urlretrieve(imgurl,"%s.jpg" % x)
#   将筛选到的图片命名为x.jpg
            print("%s.jpg" % x)
            x+=1#x自加
            time.sleep(0.5)#暂停程序0.2m，防止被屏蔽
    print("*******************************")
    print(getImg(soupl))#打印出所有图片
    print(i)
