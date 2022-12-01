package screen;

import java.awt.*;
import javax.swing.*;
import gameresource.*;
import screen.*;

class Screen extends JFrame {
	public Screen() {
		setSize(800, 500);
		setTitle("test");
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		Container ct = getContentPane();
		CardLayout cl = new CardLayout(0,0);
		ct.setLayout(cl);

		
		Game game = new Game(0);
		ct.add(game,"Game");
		cl.show(ct, "Game");
		game.start();
		game.requestFocus();
		setVisible(true);
	}
}


public class mainScreen {
	public static void main(String args[]) {
		new Screen();
	}
}
