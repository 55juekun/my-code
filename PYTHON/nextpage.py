#添加了一些新的内容使其能自动获得下一页的网址并进入
import urllib.request   #发出请求，得到网页反馈，但存在部分编码问题
from bs4 import BeautifulSoup   #作用同上，不存在编码问题
import time     #导入时间，防止因为请求网页速度过快而被屏蔽
import os
import sys
import re   #导入正则，用于筛选
#定义初始图片序号
import tkinter.messagebox#导入对话框项目
from tkinter import simpledialog
root = tkinter.Tk()

root.wm_attributes('-topmost',1)
root.withdraw()#隐藏父窗口
y=simpledialog.askstring('folder','图片所放的文件夹')#一个用于放当前图片的文件夹
url=simpledialog.askstring('url','website')#原始网址
u="E:\\实验用\\"
z=u+y
print(z)
os.mkdir(z)#新建文件夹
os.chdir(z)#将刚才建好的文件夹作为当前程序运行地址，即将图片下载至此文件夹

def re_pro():
    python = sys.executable
    os.execl(python, python, * sys.argv)
time.sleep(1)

global x
x=1#控制照片序号

def getMiddleStr(content,startStr,endStr):#定义一个函数，函数作用为选取content字符串中以startStr开始，以endStr结尾的字符串
    startIndex = content.index(startStr)#获取startStr在content中的首字符位置，index是获取第一次出现的位置，rindex是获取最后一次出现的位置
    if startIndex>=0:#获取startStr在content中的尾字符位置
        startIndex += len(startStr)
        content=content[startIndex:]
    endIndex = content.index(endStr)#获取endStr在content中的首字符位置
    return content[1:endIndex]#返回修改后的content
    
for i in range(2,50):#i控制下一页是第几页
    wb_data=urllib.request.urlopen(url)     #请求打开网页源代码
    soup = BeautifulSoup(wb_data,'lxml')    #用beautifulsoup，
#   'lxml'编译打开的网页
    soupl=soup.prettify()#将源代码格式化
    def getImg(soupl):#定义一个方法
        global x#全局变量x，用于在每一次网址变动后依然能顺序的计数
        reg=r'src="(.*?\.jpg)"'#筛选的标准为后置jpg
        imgre=re.compile(reg)#用正则先编译这个筛选
#   防止网页代码过多编译时软件死机
        imglist=re.findall(imgre,soupl)#从soupl（网页）
#   中筛选imgre（）
        imgliststr=str(imglist)#将图片链接表示为str格式
        if len(imgliststr)<10:#检测图片链接是否存在，若不存在，则将原始图片代码：即未转码的代码用于图片筛选，从而使其自动中断当前循环，10为随意写的一个数字
            finallist=imglist
        else:#若图片链接存在，则开始裁切图片链接
            mid=getMiddleStr(imgliststr,'&amp;src=','jpg')#反复查看转码后的图片链接，从而选出筛选方法
            jpgurl=mid+'jpg'#因上一句代码切掉了jpg，所以这儿加上
            finallist=jpgurl.split("!")#因jpgurl的格式是字符串，而urllib.request.urlretrieve(imgurl,"%s.jpg" % x)语句只能检索列表，故通过这个语句将其转化为
            #列表格式，split("!")函数是以引号内的!（因jpgurl中不含!，故选取它为分隔符）为分隔符分割字符串，并按照分割后的顺序依次将其输出为同一列表的不同元素
        for imgurl in finallist:#使用循环开始筛选所用图片
            urllib.request.urlretrieve(imgurl,"%s.jpg" % x)#   将筛选到的图片命名为x.jpg
            print("%s.jpg" % x)
            x+=1#x自加
            time.sleep(2)#暂停程序2s，防止被屏蔽
    print("*******************************")
    print(getImg(soupl))#打印出所有图片
    print("*******************************")
    print(i)#下一页的页码
    
    add='\"\"\"'+soupl+'\"\"\"'#格式化后的网页代码同时存在"xxx",'xxx'，直接用字符串处理函数对其处理会报错，故先将其前后都加上"""，使字符串处理函数能准确处理字符
    delcite=add.replace("'","")#因我所需代码对'"没有要求，故将代码中的'全部删去，若需保留此符号，可用别的不会造成错误的符号替换
    delenter=delcite.replace('\n','')#删去换行符
    delblank=delenter.replace(' ','')#删去空格
    tab='>%s<' %i#下一页的链接的标签，如在网页源代码中，第8页的链接为<href="https........jpg">8<....
    try:
        loc1=delblank.rindex(tab)#获取修改完成后的网页代码中标签的位置，index是获取第一次出现的位置，rindex是获取最后一次出现的位置
    except Exception as e :
        print(e)
        break
    delbehind=delblank[:loc1+1]#把标签后面的代码全部删掉，防止后文检索时检索到错误网址
    print('################################')
    loc2=delbehind.rindex('href="')#获取最后一次“href=”出现的位置，即标签中的链接出现的位置
    success=delbehind[loc2+1:]#获取到"href="与">8<"中间的代码部分，即原始标签链接
    print(success)
    url=getMiddleStr(success.replace('amp;',''),'ref="','">')#查看获取到的代码，发现"amp;"会影响链接的正常获取，故删去；再次截取中间代码，即为下一页的链接；将url经过for循环再次打开，即可
    print(url)

re_pro()


