package applettest;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//这个程序能够一直处于运行状态，随机选中一个字母并将其表现在屏幕上
/**RandomCharacters.java
 * @author 55珏坤
 *RandomCharacters下午5:16:04
 */
public class RandomCharacters  extends JApplet
	implements ActionListener{
	private String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ何钰坤";
	private final static int SIZE= 3;
	private JLabel outputs[];
	private JCheckBox checkBox[];
	private Thread threads[];
	private boolean suspended[];
	
	public void init(){
		outputs=new JLabel[SIZE];
		checkBox=new JCheckBox[SIZE];
		threads=new Thread[SIZE];
		suspended=new boolean[SIZE];
		
		Container container=getContentPane();
		container.setLayout(new GridLayout(SIZE,2,5,5));
		
		for (int count = 0; count < SIZE; count++) {
			outputs[count]=new JLabel();
			outputs[count].setBackground(Color.green);
			outputs[count].setOpaque(true);
			container.add(outputs[count]);
			
			checkBox[count]=new JCheckBox("Suspended");//suspended暂停
			checkBox[count].addActionListener(this);
			container.add(checkBox[count]);
		}
	}
	public void start() {
		for (int count = 0; count < threads.length; count++) {
			threads[count]=new Thread (new RunnableObject(),"Thread"+(count+1));
			threads[count].start();
		}
	}
	
	private int getIndex(Thread current) {
		for (int count = 0; count < threads.length; count++) {
			if (current==threads[count])
				return count;
		}
		return -1;
	}
	
	public synchronized void stop(){
		//synchronized同步，在程序中表示当点击“暂停按钮”时暂停或开始线程》》》87
		for (int count = 0; count < threads.length; count++) {
			threads[count]=null;
		}
		notifyAll();
		// 如果对象调用了notifyAll方法就会通知所有等待这个对象控制权的线程继续运行。

	}
	
	public synchronized void  actionPerformed(ActionEvent event) {
		for (int count = 0; count < checkBox.length; count++) {
			if (event.getSource()==checkBox[count]) {
				suspended[count]=!suspended[count];
				//将boolean值反转，在单击暂停键时
				outputs[count].setBackground(!suspended[count]?Color.green:Color.red);
				
				if (!suspended[count]) {
					//如果boolean值为false，则释放所有线程
					notifyAll();
				}
				return;
			}
		}
	}
	
	public class RunnableObject implements Runnable {
		public void run(){
			final Thread currentThread=Thread.currentThread();
			final int index=getIndex(currentThread);
			while (threads[index]==currentThread) {
				try {
					Thread.sleep((int)(Math.random()*1000));
					//线程休眠一段时间（0-1000ms）
					synchronized (RandomCharacters.this) {
						while (suspended[index]&&threads[index]==currentThread) {
							RandomCharacters.this.wait();
	//如果对象调用了wait方法就会使持有该对象的线程把该对象的控制权交出去，然后处于等待状态。
						}
					}
				} catch (InterruptedException interruptedException) {
					// TODO: handle exception
					System.err.println("sleep interrupted");
				}
				SwingUtilities.invokeLater(new Runnable() {
					
					
					public void run() {
						// TODO Auto-generated method stub
						char displayChar=alphabet.charAt((int)(Math.random()*29));
						//利用随机数*26表示字母的序号
						outputs[index].setText(currentThread.getName()+":"+displayChar);
					//将当前进程的名字+字母
					}
				});
			}
			System.err.println(currentThread.getName()+"terminating");//terminating结束
		//依次把结束语句的顺序记录下来，依然属于异常的情况里面（终止程序）
		}
	}
}
