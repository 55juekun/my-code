package aa.aaaa;
public class AccessProperty {
static int i;//此处不一定要定义一个确切的数，只要保证是静态变量就行
public void call() {
	System.out.println("调用 call()方法");
	for(i=0;i<3;i++) {
		System.out.print(i+" ");
		if(i==2) {
			System.out.println("\n");
		}
	}
}//这儿的代码直接删掉没有任何影响
public static void main(String[] args) {
	AccessProperty t1=new AccessProperty();//运行完一次函数后i此时为3
	AccessProperty t2=new AccessProperty();
	t2.i=60;//这儿相当于直接将函数内的i赋值为60（因为是静态变量，如果改为一般的，接下来输出就不会是60）
	System.out.print("第一个实例对象调用变量i的结果："+t1.i);
	//调用函数，并输出函数中的i值，因为上一代码将其已经赋值为60，故结果为60，上一行代码改为t1.i=60结果不变
	//再次输出i会变成61。   这儿的i++没有用啊，后面直接将其赋值为3了，或者改为+（++t1.i），或者改为+t1.i
	
	t1.call();//调用AccessProperty中的call函数，运行完后i变为3
	System.out.println("第二个实例对象调用变量i的结果："+t2.i);
	t2.call();
}
}
