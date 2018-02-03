
package aaaa;
import	java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
/**Ovalpanel.java
 * @author 55珏坤
 *Ovalpanel上午9:54:10
 */
public class SliderDemo extends JFrame{
	private JSlider diameterSlider;
	private Ovalpanel myPanel;
	
	public SliderDemo(){
		super("Slider demo");
		myPanel =new Ovalpanel();
		myPanel.setBackground(Color.yellow);
		
		diameterSlider= new JSlider(SwingConstants.HORIZONTAL,0,200,10);
		diameterSlider.setMajorTickSpacing(20);//刻度条刻度间距
		diameterSlider.setPaintTicks(true);//刻度条注释可见
		
		diameterSlider.addChangeListener(
				new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						myPanel.setDiameter(diameterSlider.getValue());
						//getvalue就是拉动鼠标来使圆的直径变化
					}
				}
		);
		Container container =getContentPane();
		container.add(diameterSlider,BorderLayout.SOUTH);
		container.add(myPanel,BorderLayout.CENTER);//此语句与上一语句一起控制刻度条的位置
		
		setSize(230, 280);
		setVisible(true);
	}
public static void main(String args[])
{	
	SliderDemo a= new SliderDemo();
	a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}