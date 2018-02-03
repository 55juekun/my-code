package phone;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.Highlighter.Highlight;
/**Client.java
 * @author 55珏坤
 *Client上午9:47:57
 */
public class Client extends JFrame{
	private JTextField	enterField;
	private JTextArea	displayArea;
	private ObjectOutputStream	output;
	private ObjectInputStream	input;
	private String message="";
	private String chatServer;
	private Socket client;
	
	public Client(String host){
		super("Client");
		chatServer=host;
		Container container=getContentPane();
		
		enterField=new JTextField();
		enterField.setText("输入你要发送的内容并键入Enter");
		enterField.setEnabled(false);
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
	public void runClient(){
		try {
			connectToServer();
			getStreams();
			processConnection();
		} catch (EOFException e) {
			// TODO: handle exception
			System.out.println("Server terminated connection");
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void getStreams()throws IOException	 {
		output=new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input=new ObjectInputStream(client.getInputStream());
		displayArea.append("\nGot I/O streams\n");
	}
	
	private void connectToServer() throws IOException{
		displayArea.setText("Attemping connection");
		client=new Socket(InetAddress.getByName("localhost"),5000);
		
		displayArea.append("\nConnected to: "+client.getInetAddress().getHostName());
	}
	private void processConnection() throws IOException{
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
		} while (!message.equals("SERVER>>>TERMINATE"));
	}
	private void closeConnection() throws IOException{
		displayArea.append("\nClosing connection");
		output.close();
		input.close();
		client.close();
	}
	
	private void sendData(String message) {
		try {
			output.writeObject("CLIENT>>>"+message);
			output.flush();
			displayArea.append("\nCLIENT>>>"+message);
			
		} catch (IOException e) {
			// TODO: handle exception
			displayArea.append("\nError writing object");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client aClient;
		if (args.length==0) {
			aClient=new Client("127.0.0.1");
		}
		else {
			aClient=new Client(args[0]);
		}
		aClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aClient.runClient();
	}

}
