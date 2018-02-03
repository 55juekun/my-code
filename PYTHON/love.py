import pylab as pl
import math
x=[]
y=[]
def love1():
    num=-math.pi
    while num<math.pi:
        sin=math.sin(num)
        cos=math.cos(num)
        y.append(13*cos-5*math.cos(2*num)-2*math.cos(3*num)-math.cos(4*num))
        x.append(16*(sin**3))
        num+=0.1
    return x,y
def love2():
    num=-math.pi
    while num<math.pi:
        y.append(math.sqrt(2*abs(num)-num**2))
        x.append(-2.14*math.sqrt(1.4142135623730951-math.sqrt(abs(num))))
        num+=0.1
    return x,y
(z,p)=love1()
pl.plot(z,p)# use pylab to plot x and y
pl.show()# show the plot on the screen
