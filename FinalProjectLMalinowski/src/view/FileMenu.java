package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import utils.FileIO;

public class FileMenu implements ActionListener{
	private JMenu fileMenu;
	private JMenuItem itemNew;
	private JMenuItem itemOpen;
	private JMenuItem itemSave;
	private JMenuItem itemClose;
	private JMenuItem itemExit;	
	
	public FileMenu() {
		fileMenu = new JMenu("file");
		JMenuItem[] menuItemsArr = {
			itemNew = new JMenuItem("new"),
			itemOpen = new JMenuItem("open"),
			itemClose = new JMenuItem("close"),
			itemSave = new JMenuItem("save"),
			itemExit = new JMenuItem("exit")
		};
		for(int i=0; i<menuItemsArr.length; i++) {
			fileMenu.add(menuItemsArr[i]);
			menuItemsArr[i].addActionListener(this);
		}
	}
	public JMenu getFileMenu() {return fileMenu;}
	public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); 
        FileIO fileIO = new FileIO();
        JFrame frame = Editor.getFrame();
        JTextArea textArea = Editor.getTextArea();
        String fileName = "";
        if (command.equals("new")){
        	textArea.setText("");
        	fileName = "new file";
        	if(!textArea.isVisible())textArea.setVisible(true);
        } else if (command.equals("close")){
        	fileName = "no selceted file";
        	textArea.setText("");
        	textArea.setVisible(false);
        }
        else if (command.equals("exit"))System.exit(0);
        else if (command.equals("save"))fileName = fileIO.save(textArea.getText());
        else if(command.equals("open")) {
        	if(!textArea.isVisible())textArea.setVisible(true);
        	fileName = fileIO.open();	
        }
        frame.setTitle(fileName);
	}
	
}
