package utils;

import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import view.Editor;

public class EditUtils {
	
	private UndoManager editManager;
	private JTextArea textArea;
	
	public EditUtils(){
		editManager = new UndoManager();
		textArea = Editor.getTextArea();
    	textArea.getDocument().addUndoableEditListener(
	        new UndoableEditListener() {
	            public void undoableEditHappened(UndoableEditEvent e) {
	                editManager.addEdit(e.getEdit());
            }
        });
	}
	
	public boolean editUndo() {
        if (editManager.canUndo()) {
        	editManager.undo();
        	return true;
        } return false;
	}
	public boolean editRedo() {
		if (editManager.canRedo()) {
			editManager.redo();
			return true;
		} else return false;
	}
}
