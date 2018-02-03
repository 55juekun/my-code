/**
 * 
 */
package bb;
import java.awt.*;
import java.awt.event.*;
import java.security.DigestException;
import java.text.*;
import javax.swing.*;
import java.lang.Exception;
/**DivideByZeroTest.java
 * @author 55珏坤
 *DivideByZeroTest下午2:04:06
 */
public class DivideByZeroTest extends JFrame implements ActionListener{
	private JTextField inputField1,inputField2,outputField;
	private int num1,num2;
	private double result;
	
	public  DivideByZeroTest() {
		super("Demonstrating MyException");
		Container container=getContentPane();
		container.setLayout(new GridLayout(3,2));
		
		container.add(new JLabel("Enter numerator",SwingConstants.RIGHT));
		inputField1= new JTextField();
		container.add(inputField1);
		
		container.add(new JLabel("Enter denominator and press Enter",SwingConstants.RIGHT));
		inputField2= new JTextField();
		container.add(inputField2);
		inputField2.addActionListener(this);
		
		container.add(new JLabel("Result",SwingConstants.RIGHT));
		outputField= new JTextField();
		container.add(outputField);
		
		setSize(425,100);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent event) {
		DecimalFormat precision3=new DecimalFormat("0.000");
		outputField.setText("");
		
		try {
			num1=Integer.parseInt(inputField1.getText());
			num2=Integer.parseInt(inputField2.getText());
			result=quotient(num1,num2);
			outputField.setText(precision3.format(result));
			
		} catch (NumberFormatException numberFormatException) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this,
	        "you must enter two integers",
	        "invalid Number Format",JOptionPane.ERROR_MESSAGE);
		}
		catch (ArithmeticException arithmeticException) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this,
			arithmeticException.toString(),
	        "Arithmetic MyException",JOptionPane.ERROR_MESSAGE);
		}
	}
	public double quotient(int numerator,int denominator)
	throws Di
	{
		if(denominator==0)
			throw new Di();
		return (double) numerator/denominator;
		
	}
	public static void main(String args[]) {
		// TODO Auto-generated method stub
		DivideByZeroTest a=new DivideByZeroTest();
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
