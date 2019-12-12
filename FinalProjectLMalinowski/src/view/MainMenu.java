package view;

import javax.swing.JMenuBar;

public class MainMenu {
	private JMenuBar menuBar;
	
	public MainMenu() {
		menuBar = new JMenuBar();
		menuBar.add(new FileMenu().getFileMenu());
		menuBar.add(new EditMenu().getEditMenu());
		menuBar.add(new ViewMenu().getViewMenu());
	}
	public JMenuBar getMenuBar() {return menuBar;}
}
