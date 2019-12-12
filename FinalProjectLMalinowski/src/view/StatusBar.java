package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import utils.TextParsing;


public class StatusBar{
	
	private JLabel status;
	private String[] statusArr;
	private JLabel wordCount;
	private JLabel sentanceCount;
	private JLabel fleschScore;
	private JTextArea textArea;
	private JLabel [] lables = {
		wordCount = new JLabel("word count : 0"),
		sentanceCount = new JLabel("sentance count : 0"),
		fleschScore = new JLabel("flesch score : 0.0")	
	};
	private String[] statusBarLocations = {
		BorderLayout.WEST,
		BorderLayout.CENTER,
		BorderLayout.EAST
	};
	
	
    public StatusBar() {
    	textArea = Editor.getTextArea();
    	status = new JLabel();
    	status.setPreferredSize(new Dimension(0, 20));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.layoutContainer(Editor.getFrame());
        borderLayout.setHgap(50);
        status.setLayout(borderLayout);
		for(int i=0; i<lables.length; i++) 
	        status.add(lables[i],statusBarLocations[i]);		
		textArea.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				getStatusBarData();
			}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
        });
		textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
				getStatusBarData();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {				
            	getStatusBarData();
            }
            @Override
            public void changedUpdate(DocumentEvent arg0) {
            	getStatusBarData();
            }
        });
    }
    public JLabel getStatusBar() {return status;}
    public void getStatusBarData() {
    	TextParsing textParsing = new TextParsing(textArea.getText());
		statusArr = textParsing.getStatusArr();
		for(int j=0; j<statusArr.length; j++) {
			lables[j].setText(statusArr[j]);
		}
    }
    
}