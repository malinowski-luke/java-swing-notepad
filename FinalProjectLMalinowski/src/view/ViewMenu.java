package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import utils.TextParsing;

public class ViewMenu implements ActionListener {
	private JMenu viewMenu;
	private JMenuItem itemWordCount;
	private JMenuItem itemSentanceCount;
	private JMenuItem itemFleschScore;
	
	public ViewMenu() {
		viewMenu = new JMenu("view");
		JMenuItem[] menuItemsArr = {
			itemWordCount = new JMenuItem("word count"),
			itemSentanceCount = new JMenuItem("sentance count"),
			itemFleschScore = new JMenuItem("flesch score")
		};
		for(int i=0; i<menuItemsArr.length; i++) {
			viewMenu.add(menuItemsArr[i]);
			menuItemsArr[i].addActionListener(this);
		}
	}
	public JMenu getViewMenu() {return viewMenu;}
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		JFrame frame = Editor.getFrame();
		JTextArea textArea = Editor.getTextArea();
		TextParsing textParsing = new TextParsing(textArea.getText());
		String[] msgArr = textParsing.getMsgArr();
		if(command.equals("word count"))JOptionPane.showMessageDialog(frame,msgArr[0]);
		else if(command.equals("sentance count"))JOptionPane.showMessageDialog(frame,msgArr[1]);
		else if(command.equals("flesch score"))JOptionPane.showMessageDialog(frame,msgArr[2]);
	}
}
