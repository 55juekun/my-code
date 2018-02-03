package phone;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/**Server.java
 * @author 55珏坤
 *Server下午9:03:49
 */
public class Server extends JFrame{
	private JTextField	enterField;
	private JTextArea	displayArea;
	private ObjectOutputStream	output;
	private ObjectInputStream	input;
	private ServerSocket	server;
	private Socket	connection;
	private int counter=1;
	
	public Server(){
		super("Server");
		Container container=getContentPane();
		
		enterField=new JTextField();
		enterField.setEnabled(false);
		enterField.setText("输入你要发送的内容并键入Enter");
		enterField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sendData(e.getActionCommand());
			}
		});
		container.add(enterField,BorderLayout.NORTH);
		
		displayArea=new JTextArea();
		container.add(new JScrollPane(displayArea),BorderLayout.CENTER);
		
		setSize(300,150);
		setVisible(true);
	}
	public void runServer(){
		try {
			server=new ServerSocket(5000,100);
			while (true) {
				waitForConnection();
				getStreams();
				processConnection();
				closeConnection();
				++counter;
			}
		} catch (EOFException e) {
			// TODO: handle exception
			System.out.println("Client terminated connection");
		}
		catch (IOException ioException) {
			// TODO: handle exception
			ioException.printStackTrace();
		}
	}
	private void waitForConnection() throws IOException{
		displayArea.setText("Waiting for connection");
		connection=server.accept();
		displayArea.append("\nConnection "+ counter+" received from:"+
		connection.getInetAddress().getHostName());
	}
	private void getStreams() throws IOException{
		output=new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input=new ObjectInputStream(connection.getInputStream());
		displayArea.append("\nGot I/O streams\n");
	}
	private void processConnection() throws IOException{
		String message="SERVER>>> Connection successful";
		output.writeObject(message);
		output.flush();
		enterField.setEnabled(true);
		do {
			try {
				message=(String) input.readObject();
				displayArea.append("\n"+message);
				displayArea.setCaretPosition(displayArea.getText().length());
			} catch (ClassNotFoundException classNotFoundException) {
				// TODO: handle exception
				displayArea.append("\nUnkown object type received");
			}
		} while (!message.equals("CLIENT>>>TERMINATE"));
	}
	
	private void closeConnection() throws IOException{
		displayArea.append("\nUser terminated connection");
		enterField.setEditable(false);
		output.close();
		input.close();
		connection.close();
	}
	
	private void sendData(String message) {
		try {
			output.writeObject("SERVER>>>"+message);
			output.flush();
			displayArea.append("\nSERVER>>>"+message);
			
		} catch (IOException e) {
			// TODO: handle exception
			displayArea.append("\nError writing object");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server aServer=new Server();
		aServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aServer.runServer();
	}

}
