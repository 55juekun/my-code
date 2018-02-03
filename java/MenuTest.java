
package aaaa;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

/**DrawShapes.java
 * @author 55珏坤
 *DrawShapes下午8:22:14
 */
public class MenuTest extends JFrame{
	private Color colorValues[] ={Color.black,Color.blue,Color.red,Color.green};
	
	private JRadioButtonMenuItem colorItems[],fonts[];
	private JCheckBoxMenuItem styleItems[];
	private JLabel displayLabel;
	private ButtonGroup fontGroup,colorGroup;
	private int style;
	/*22-57建立一个File菜单，并把它添加到菜单栏中。File包含一个About菜单项，当选中它时，
	  将显示一个消息对话框。File中还有一个Exit菜单项*/
	public MenuTest(){
		super("using JMenus");
		
		JMenu fileMenu=new JMenu("File");//将File字符串传递给构造函数作为菜单名
		fileMenu.setMnemonic('F');//setMnemonic设置快捷键
		
		JMenuItem aboutItem=new JMenuItem("About.....");
		aboutItem.setMnemonic('A');
		
		aboutItem.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JOptionPane.showMessageDialog(MenuTest.this,
				//showMessageDialog的第一个语句通常为空，以对话框的父窗口来确定本窗口的位置
				//如果为null，则对话框在屏幕中央，本例用this指示MenuTest
								"This is an example\nof using menus","About",JOptionPane.PLAIN_MESSAGE);
					}
				}
		);
		fileMenu.add(aboutItem);//将aboutitem添加到fileMenu中
		
		JMenuItem exitItem=new JMenuItem("Exit");
		exitItem.setMnemonic('x');
		
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		}
				);
		fileMenu.add(exitItem);
		
		JMenuBar bar =new JMenuBar();
		setJMenuBar(bar);
		bar.add(fileMenu);
		
		JMenu formatMenu=new JMenu("Format");
		formatMenu.setMnemonic('r');
		
		String colors[]={"black","blue","red","green"};
		
		JMenu colorMenu=new JMenu("color");
		colorMenu.setMnemonic('c');
		
		colorItems=new JRadioButtonMenuItem[colors.length];//73-74一起
		colorGroup =new ButtonGroup();//确保 color子菜单中在一个时刻只能有一个选项
		ItemHandler itemHandler=new ItemHandler();
		
		for(int count=0;count<colors.length;count++){
			colorItems[count]=
					new JRadioButtonMenuItem(colors[count]);
			colorMenu.add(colorItems[count]);
			colorGroup.add(colorItems[count]);
			colorItems[count].addActionListener(itemHandler);
		}
		
		colorItems[0].setSelected(true);
		
		formatMenu.add(colorMenu);
		formatMenu.addSeparator();//addSeparator()添加分隔符，此语句为在color和format间添加
		
		String fontNames[]={"Serif","Monospaced","ScansSerif"};
		
		JMenu fontMenu=new JMenu("Font");
		fontMenu.setMnemonic('n');
		
		fonts =new JRadioButtonMenuItem[fontNames.length];
		fontGroup=new ButtonGroup();
		
		for(int count=0;count<fonts.length;count++){
			fonts[count]=new JRadioButtonMenuItem(fontNames[count]);
			fontMenu.add(fonts[count]);
			fontGroup.add(fonts[count]);
			
			fonts[count].addActionListener(itemHandler);
		}
		fonts[0].setSelected(true);
		fontMenu.addSeparator();
		String styleNames[]={"bold","italic"};
		
		styleItems=new JCheckBoxMenuItem[styleNames.length];
		StyleHandler styleHandler=new StyleHandler();
		
		for(int count=0;count<styleNames.length;count++){
			styleItems[count]=new JCheckBoxMenuItem(styleNames[count]);
			fontMenu.add(styleItems[count]);
			styleItems[count].addItemListener(styleHandler);
		}
		formatMenu.add(fontMenu);
		
		bar.add(formatMenu);
		
		displayLabel=new JLabel("sample text",SwingConstants.CENTER);
		displayLabel.setForeground(colorValues[0]);
		displayLabel.setFont(new Font("timesroman",Font.PLAIN,72));
		getContentPane().setBackground(Color.cyan);
		getContentPane().add(displayLabel,BorderLayout.CENTER);
		
		setSize(500,200);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MenuTest a=new MenuTest();
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private class ItemHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			for (int i = 0; i < colorItems.length; i++) 
				if (colorItems[i].isSelected()) {
					displayLabel.setForeground(colorValues[i]);
					break;
				}
			for (int j = 0; j < fonts.length; j++) 
				if(event.getSource()==fonts[j]){
					displayLabel.setFont(new Font(fonts[j].getText(),style,72));
					break;
				}
			repaint();	
		}	
	}
			private class StyleHandler implements ItemListener{
				public void itemStateChanged(ItemEvent event) {
					style =0;
					if (styleItems[0].isSelected()) 
						style+=Font.BOLD;
					if (styleItems[1].isSelected()) 
						style+=Font.ITALIC;
					displayLabel.setFont(new Font(displayLabel.getFont().getName(),style,72));
					repaint();
						}
					}
				}		
			