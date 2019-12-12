package view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import utils.EditUtils;

public class EditMenu implements ActionListener{
	private JMenu editMenu;
	private JMenuItem itemCut;
	private JMenuItem itemCopy;
	private JMenuItem itemPaste;
	private JMenuItem itemSelectAll;
	private JMenuItem itemUndo;
	private JMenuItem itemRedo;
	private JMenuItem[] menuItemsArr = {
		itemUndo = new JMenuItem("undo"),
		itemRedo = new JMenuItem("redo"),
		itemCut = new JMenuItem("cut"),
		itemCopy = new JMenuItem("copy"),
		itemPaste = new JMenuItem("paste"),
		itemSelectAll = new JMenuItem("select all")
	};
	private EditUtils editUtils;
	
	public EditMenu() {
		editUtils = new EditUtils();
		editMenu = new JMenu("edit");
		for(int i=0; i<menuItemsArr.length; i++) {
			if(i==2)editMenu.addSeparator();
			editMenu.add(menuItemsArr[i]);
			menuItemsArr[i].addActionListener(this);
		}
	}
	public JMenuItem[] getEditMenuItems() {return menuItemsArr;}
	public JMenu getEditMenu() {return editMenu;}
	public JMenuItem getItemUndo() {return itemUndo;}
	public JMenuItem getItemRedo() {return itemRedo;}
	public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); 
        Frame  frame = Editor.getFrame();
    	JTextArea textArea = Editor.getTextArea();
    	if (command.equals("cut")) textArea.cut(); 
        else if (command.equals("copy")) textArea.copy(); 
        else if (command.equals("paste")) textArea.paste();
        else if (command.equals("undo")) {
        	if(editUtils.editUndo());//nop
        	else JOptionPane.showMessageDialog(frame,"clipboard empty can't preform undo operation.");
        }
        else if (command.equals("redo")) {
        	if(editUtils.editRedo());//nop 
        	else JOptionPane.showMessageDialog(frame,"clipboard empty can't preform redo operation.");
        }
        else if (command.equals("select all")) textArea.selectAll();
	}
}
