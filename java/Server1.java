package mail;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/**Server.java
 * @author 55珏坤
 *Server上午10:50:26
 */
public class Server extends JFrame{
	private JTextArea displayArea;
	private DatagramPacket sendPacket,receivePacket;
	private DatagramSocket socket;
	
	public Server(){
		super("Server");
		displayArea=new JTextArea();
		getContentPane().add(new JScrollPane(displayArea),BorderLayout.CENTER);
		setSize(400,300);
		setVisible(true);
		try {
			socket=new DatagramSocket(5000);
		} catch (SocketException socketException) {
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
				sendPacketToClient();
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
		
	}
	private void sendPacketToClient() throws IOException {
		displayArea.append("\n\nEcho data to client...");
		sendPacket=new DatagramPacket(receivePacket.getData(), 
	receivePacket.getLength(),receivePacket.getAddress(),receivePacket.getPort());
		
		socket.send(sendPacket);
		displayArea.append("Packet sent\n");
		displayArea.setCaretPosition(displayArea.getText().length());
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server aServer=new Server();
		aServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aServer.waitForPackets();
	}

}
