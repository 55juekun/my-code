import os
import tkinter#.messagebox
from tkinter import simpledialog
import pip
#print(pip.pep425tags.get_supported())
''' pip安装的文件一般在\Lib\site-packages里面
'''
root = tkinter.Tk()
#root.withdraw()#隐藏父窗口
def folder():
    y=simpledialog.askstring('folder','图片所放的文件夹')
    x="C:\\Users\\lenovo\\AppData\\Local\\Programs\\Python\\Python35-32\\code\\"
    z=x+y
    print(z)
    os.mkdir(z)
    os.chdir(z)
def url():
    o=simpledialog.askstring('url','website')
    print(o)

btn1= tkinter.Button(root, text='new folder', command=folder)
btn2= tkinter.Button(root, text='url', command=url)


btn1.pack(side='right')
btn2.pack(side='left')

root.mainloop()
