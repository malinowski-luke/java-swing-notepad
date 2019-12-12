package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import utils.EditUtils;

public class Editor extends JFrame{
	
	private static JTextArea textArea;
	private static JFrame frame;
	private JScrollPane scrollPane;
	
	public Editor() {
		textArea = new JTextArea();
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme()); 
		} catch(Exception err) {}
		if(!textArea.hasFocus()) textArea.requestFocusInWindow();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setMargin(new Insets(5,5,5,10));
		//right click context menu event listener
		textArea.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent me) {
				if(SwingUtilities.isRightMouseButton(me))new ContextArea().showPopup(me);
			}
		});
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);	
		frame = new JFrame("new file");
		frame.setLayout(new BorderLayout());
		frame.setJMenuBar(new MainMenu().getMenuBar());
		frame.getContentPane().add(new StatusBar().getStatusBar(), java.awt.BorderLayout.SOUTH);
		frame.add(scrollPane);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setSize(screenSize.width/2,screenSize.height/2);
		frame.setLocationRelativeTo(null);
		//sets red x to System.exit(0) as default action
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.show();
	}
	public static JTextArea getTextArea() {
		return textArea;
	}
	
	public static JFrame getFrame() {
		return frame;
	}

}
