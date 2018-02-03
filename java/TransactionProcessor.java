import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.RandomAccess;

import javax.security.auth.kerberos.DelegationPermission;
import javax.swing.*;

import com.deitel.jhtp4.ch16.*;
package applettest;

/**TransactionProcessor.java
 * @author 55珏坤
 *TransactionProcessor下午3:42:53
 */
public class TransactionProcessor extends JFrame{
	private UpdateDialog updateDialog;
	private NewDialog newDialog;
	private DeleteDialog deleteDialog;
	private JMenuItem newItem,updateItem,deleteItem,openItem,exitItem;
	private JDesktopPane desktop;
	private RandomAccessFile file;
	private RandomAccessAccountRecord record;
	
	public TransactionProcessor(){
		super("Transation Processor");
		
		desktop=new JDesktopPane();
		getContentPane().add(desktop);
		
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(fileMenu);
		
		newItem=new JMenuItem("New Record");
		newItem.setEnabled(false);
		
		newItem.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						newDialog.setVisible(true);
					}
				}
		);
		
		updateItem=new JMenuItem("Update Record");
		updateItem.setEnabled(false);
		updateItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateDialog.setVisible(true);
			}
		}
		);
		
		deleteItem=new JMenuItem("Delete Record");
		deleteItem.setEnabled(false);
		deleteItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteDialog.setVisible(true);
			}
		}
		);
		
		openItem=new JMenuItem("New/Open File");
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean opened=openFile();
				if(!opened)
					return;
				
				openItem.setEnabled(false);
				
				updateDialog=new UpdateDialog(file);
				desktop.add(updateDialog);
				
				deleteDialog=new DeleteDialog(file);
				desktop.add(deleteDialog);
				
				newDialog=new NewDialog(file);
				desktop.add(newDialog);
			}
		}
		);
		
		exitItem=new JMenuItem("Exit");
		exitItem.setEnabled(true);
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				closeFile();
			}
		}
		);
		
		fileMenu.add(openItem);
		fileMenu.add(newItem);
		fileMenu.add(updateItem);
		fileMenu.add(deleteItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		setSize(400,260);
		setVisible(true);
		}
	
	private boolean OpenFile(){
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int result=fileChooser.showOpenDialog(this);
		
		if (result==JFileChooser.CANCEL_OPTION) 
			return false;

		File fileName=fileChooser.getSelectedFile();
		
		if (fileName==null || fileName.getName().equals("")) {
			JOptionPane.showMessageDialog(this, "Invalid File Name","Invalid File Name",JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		else {
			try {
				file=new RandomAccessFile(fileName, "rw");
				openItem.setEnabled(false);
				newItem.setEnabled(false);
				updateItem.setEnabled(true);
				deleteItem.setEnabled(true);
			} catch (IOException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "File dosen't exist","Invalid File Name",JOptionPane.ERROR_MESSAGE);
				
				return false;
			}
		}
		return true;
	}
	private void closeFile(){
		try {
			if (file!=null) 
				file.close();
			System.exit(0);
		} catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Error closing file","Error",JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TransactionProcessor();
	}
	
}

class UpdateDialog extends JInternalFrame{
	private RandomAccessFile file;
	private BankUI userInterface;
	
	private UpdateDialog(RandomAccessFile updateFile){
		super("Update Record");
		file=updateFile;
		
		userInterface=new BankUI(5);
		
	}
}
