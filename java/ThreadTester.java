/**
 * 
 */
package bb;

/**ThreadTester.java
 * @author 55珏坤
 *ThreadTester下午2:11:50
 */
public class ThreadTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintThread thread1,thread2,thread3,thread4;
		
		thread1=new PrintThread("线程1");
		thread2=new PrintThread("线程2");
		thread3=new PrintThread("线程3");
		thread4=new PrintThread("线程4");
		
		System.err.println("\n正在开始线程");
		//System.err使用的是未缓冲的输出，消息一旦产生就会被输出，而System.out可能并不能立即输出
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
		System.err.println("线程已经开始\n");
		
	}
}
class PrintThread extends Thread{
	private int sleepTime;
	public PrintThread(String name){
		super(name);
		sleepTime=(int) (Math.random()*5000);
		System.err.println("Name: "+getName()+"; sleep: "+sleepTime);
	}
	public void run(){
		try {
			System.err.println(getName()+" 将要休眠");
			
			Thread.sleep(sleepTime);
		} catch (InterruptedException interruptedException) {
			// TODO: handle exception
			System.err.println(interruptedException.toString());
		}
		System.err.println(getName()+" 已经休眠");
	}
}

