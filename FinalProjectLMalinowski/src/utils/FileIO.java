package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;



import view.Editor;
import view.StatusBar;

public class FileIO {
	private JFileChooser fileChooser;
	private JFrame frame;
	private JTextArea textArea;
	
	public FileIO() {
		fileChooser = new JFileChooser("f:");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text","txt"));
        fileChooser.setAcceptAllFileFilterUsed(false);
		frame = Editor.getFrame();
		textArea = Editor.getTextArea();
	}
	
	public String save(String text) {
		int r = this.fileChooser.showSaveDialog(null); 
		if (r == JFileChooser.APPROVE_OPTION) {
	    	File file = new File(this.fileChooser.getSelectedFile().getAbsolutePath()+".txt");
			String fileName = "";
	    	try {
	    		fileName = this.fileChooser.getSelectedFile().getName()+".txt";
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,false));
				bufferedWriter.write(text); 
				bufferedWriter.flush(); 
				bufferedWriter.close();
				return fileName;
			} catch (IOException e) {
		        JOptionPane.showMessageDialog(this.frame, e.getMessage()); 
			}
	    } else JOptionPane.showMessageDialog(this.frame,"user cancelled operation");
	    return "new file";
	}
	public String open() {
		int r = this.fileChooser.showOpenDialog(null); 
		if (r == JFileChooser.APPROVE_OPTION) {
			FileReader fileReader = null;
			String fileName = "";
			try {
				fileReader = new FileReader(this.fileChooser.getSelectedFile().getAbsolutePath());
				fileName = this.fileChooser.getSelectedFile().getName();
			} catch (FileNotFoundException e1) {
		        JOptionPane.showMessageDialog(this.frame, e1.getMessage()); 
			}
			try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				JTextArea textAreaTemp = new JTextArea();
				long start = System.currentTimeMillis();
				textAreaTemp.read(bufferedReader,null);
				textArea.setText(textAreaTemp.getText());
				bufferedReader.close();
				long elapsedTimeMillis = System.currentTimeMillis()-start;
				float elapsedTimeSec = elapsedTimeMillis/1000F;
				loadingLogTxtFile(fileName,elapsedTimeSec);
				return fileName;
			} catch (IOException e) {
		        JOptionPane.showMessageDialog(this.frame, e.getMessage()); 
			}
		} else JOptionPane.showMessageDialog(this.frame,"user cancelled operation");
		return "new file";
	}
	public void loadingLogTxtFile(String fileName,float time) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
		String textToAppend = "\nFile " +fileName+" opened in "+time+" seconds;";
		String logFilePath = "/Users/lukaszmalinowski/work/cse-218/FinalProjectLMalinowski/src/app_log/log.txt";
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(logFilePath, true));
			bufferedWriter.newLine();
			bufferedWriter.write(formatter.format(date));
			bufferedWriter.write(textToAppend);
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 
	}
}