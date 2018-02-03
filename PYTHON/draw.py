from PIL import Image
import random
def rad(x,y,z):
    R=int((x+random.random()*40)%256)
    G=int((y+random.random()*40)%256)
    B=int((z+random.random()*40)%256)
    return R,G,B
def radrad():
    R=int(random.random()*256)
    G=int(random.random()*256)
    B=int(random.random()*256)
    return R,G,B
(r,g,b)=radrad()
img=Image.new("RGB",(200,200),(r,g,b))
(X,Y,Z)=img.getpixel((0,0))
print(X,Y,Z)
def forloop():
    for x in range(0,200):
        for y in range(0,200):
            if x>0 and y>0 and x<100 and y<100:
                (j,k,l)=img.getpixel((random.random()*x,random.random()*y))
                (R,G,B)=rad(j,k,l)
            elif x>100 or y>100:
                (j,k,l)=img.getpixel((random.random()*100,random.random()*100))
                (R,G,B)=rad(j,k,l)
            else:
                (R,G,B)=(X,Y,Z)
            loc=img.putpixel((x,y),(R,G,B))
    print(img.getpixel((0,0)))
    print(img.getpixel((8,8)))
forloop()
img.save("a.jpeg","JPEG")
#img.putpixel((10,10)(255,0,0,0))
