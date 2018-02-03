package bb;
import java.text.DecimalFormat;//与sharedcell一起的
import javax.swing.*;
/**HoldIntegerSynchronized.java
 * @author 55珏坤
 *HoldIntegerSynchronized下午2:40:31
 */
public class HoldIntegerSynchronized {
	private int sharedInt[]={-1,-1,-1,-1,-1};
	
	private boolean writeable= true;
	private boolean readable=false;
	private int readLocation=0,writeLocation=0;
	
	private JTextArea outputArea;
	public HoldIntegerSynchronized (JTextArea output)
	{
		outputArea =output;
	}
	public synchronized void setSharedInt(int value){
		while(!writeable){
			try{
				SwingUtilities.invokeLater
						(new UpdateThread(outputArea,"等待产生"+value));
						wait();
			}
			catch (InterruptedException e) {
				// TODO: handle exception
				System.err.println(e.toString());
			}
		}
		sharedInt[writeLocation]=value;
		
		readable=true;
		
		SwingUtilities.invokeLater
		(new UpdateThread(outputArea,"\n已产生"+value+"进入细胞"+writeLocation) );
		writeLocation=(writeLocation+1)%5;
		SwingUtilities.invokeLater
		(new UpdateThread(outputArea,"\n写"+writeLocation+"\n读"+readLocation) );
		displayBuffer(outputArea,sharedInt);
		
		if (writeLocation==readLocation) {
			writeable=false;
			
			SwingUtilities.invokeLater(new UpdateThread(outputArea,"\nBUFFER FULL"));
			
		}
		notify();
	}
	public synchronized int getSharedInt(){
		int value;
		while (!readable) {
			try {
				SwingUtilities.invokeLater
				(new UpdateThread(outputArea,"\n等待继续"));
				wait();
			} catch (InterruptedException exception) {
				// TODO: handle exception
				System.err.println(exception .toString());
			}
		}
		writeable=true;
		value=sharedInt[readLocation];
		
		SwingUtilities.invokeLater
		(new UpdateThread(outputArea,"\n消费"+value+"来自细胞"+readLocation));
		readLocation=(readLocation+1)%5;
		SwingUtilities.invokeLater
		(new UpdateThread(outputArea,"\n写"+writeLocation+"\n读"+readLocation) );
		displayBuffer(outputArea,sharedInt);
		
		if (writeLocation==readLocation) {
			writeable=false;
			
			SwingUtilities.invokeLater(new UpdateThread(outputArea,"\nBUFFER EMPTY"));
			
		}
		notify();
		return value;
	}
	public void displayBuffer(JTextArea outputArea,int buffer[]) {
		DecimalFormat formatNumber=new DecimalFormat(" #;-#");
		StringBuffer outputbBuffer=new StringBuffer();
		for (int count = 0; count < buffer.length; count++) {
			outputbBuffer.append(""+formatNumber.format(buffer[count]));
			
			SwingUtilities.invokeLater
			(new UpdateThread(outputArea,"\tbuffer: "+outputBuffer));
		}
	}}