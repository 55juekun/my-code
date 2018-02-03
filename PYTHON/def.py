def x(num1):
    print(num1)
    def y(num2):
        return num1+num2
    return y#return y的话是返回y的结果，y（）的话是y的方法
q=x(2)
print('x+y='+str(q(3)))
