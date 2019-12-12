package view;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class ContextArea {
	private JPopupMenu popupMenu;
	public ContextArea() {
		popupMenu = new JPopupMenu();
		EditMenu editMenu = new EditMenu();
		JMenuItem[] editMenuArr = editMenu.getEditMenuItems();
		for(int i=0; i<editMenuArr.length; i++){
			if(i==5)popupMenu.addSeparator();
			if(i!=0&&i!=1) popupMenu.add(editMenuArr[i]);
		}
	}
	public void showPopup(MouseEvent me) {
		popupMenu.show(me.getComponent(), me.getX(), me.getY());
 	}
}	
