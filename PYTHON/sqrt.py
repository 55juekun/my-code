##用渐进法求某个数的平方根举例：1x1<2,2x2>2，所以第一位取1,
##开始第二位，1.1x1.1<2,  1.2x1.2<,   1.3x1.3<2   1.4x1.4<2   1.5x1.5>2,第二位取5
main=0##主要的值
b=0##用于每次加0.1^x
c=0##次要的值
x=0##可信值
goal=int(input("求哪个数的平方根？"))#目标值,即求目标值的平方根
for k in range(200):
    if x<10:#x<13,则可信值为12，前12位数可信
        if (main+c)**2 <goal:
            b=1*(0.1**x)
            c=c+b
        elif(main+c)**2 >goal:
            main=main+c-b
            x+=1
            c=0
            continue
        else:
            print(main+c)
            break
    else:
        break
print(main)
import math
print('******************')
print(math.sqrt(goal))
input()
