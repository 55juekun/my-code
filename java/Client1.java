package mail;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/**Client.java
 * @author 55珏坤
 *Client上午11:16:24
 */
public class Client extends JFrame{
	private JTextField	enterField;
	private JTextArea	displayArea;
	private DatagramPacket sendPacket,receivePacket;
	private DatagramSocket socket;
	
	public Client(){
		super("Client");
		Container container=getContentPane();
		enterField=new JTextField("Type message here");
		enterField.addActionListener(
				new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					displayArea.append("\nSending packet containing: "+e.getActionCommand()+"\n");
					
					String message=e.getActionCommand();
					byte data[]=message.getBytes();
					
					sendPacket=new DatagramPacket(data, data.length,InetAddress.getLocalHost(),5000);
					socket.send(sendPacket);
					
					displayArea.append("Packet sent\n");
					displayArea.setCaretPosition(displayArea.getText().length());
					
				} catch (IOException ioException) {
					// TODO: handle exception
					displayArea.append(ioException.toString()+"\n");
					ioException.printStackTrace();
				}
			}
		}
	);
		container.add(enterField,BorderLayout.NORTH);
		displayArea=new JTextArea();
		container.add(new JScrollPane(displayArea),BorderLayout.CENTER);
		setSize(400, 300);
		setVisible(true);
		
		try {
			socket=new DatagramSocket();
		} catch (SocketException	socketException) {
			// TODO: handle exception
			socketException.printStackTrace();
			System.exit(1);
		}
	}
	public void waitForPackets() {
		while (true) {
			try {
				byte data[]=new byte[100];
				
				receivePacket=new DatagramPacket(data, data.length);
				socket.receive(receivePacket);
				displayPacket();
			} catch (IOException e) {
				// TODO: handle exception
				displayArea.append(e.toString()+"\n");
				e.printStackTrace();
			}
		}
	}
	private void displayPacket() {
		displayArea.append("\nPacket recevied: "+"\nFrom host: "
	+receivePacket.getAddress()+"\nHost port: "+receivePacket.getPort()
	+"\nLength: "+receivePacket.getLength()+"\nContaining:\n\t"
	+new String(receivePacket.getData(),0,receivePacket.getLength()));
		
		displayArea.setCaretPosition(displayArea.getText().length());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client aClient=new Client();
		aClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aClient.waitForPackets();
	}

}
