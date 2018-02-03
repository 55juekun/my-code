package bb;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
/**SharedCell.java
 * @author 55珏坤
 *SharedCell下午3:29:43
 */
public class SharedCell extends JFrame{
	public SharedCell(){
		super("hhshshhs");
		JTextArea outputArea =new JTextArea(20,30);
		getContentPane().add(new JScrollPane(outputArea));
		
		setSize(400,400);
		show();
		
		HoldIntegerSynchronized sharedObject=
				new HoldIntegerSynchronized(outputArea);
		
		ProduceInteger producer=
				new ProduceInteger(sharedObject,outputArea);
		
		ConsumeInteger consumer=
				new ConsumeInteger(sharedObject,outputArea);
		
		producer.start();
		consumer.start();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SharedCell aCell=new SharedCell();
		aCell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
