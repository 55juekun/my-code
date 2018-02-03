import os
path=r"C:\Users\lenovo\Desktop\img"
i=1
for file in os.listdir(path):
    if os.path.isfile(os.path.join(path,file))==True:
        new_name=file.replace(file,"%d.jpg"%i)
        os.rename(os.path.join(path,file),os.path.join(path,new_name))
        i+=1
