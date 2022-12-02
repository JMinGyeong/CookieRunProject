package screen;

import java.awt.*;
import javax.swing.*;
import gameresource.*;
import screen.*;

class Screen extends JFrame {
	JFrame frame;
	Game game;
	Info info;
	Join join;
	Login login;
	StartBeforeLogin startbeforelogin; // 로그인 하기 전 시작 화면
	public Container ct;
	public CardLayout cl;
	
	public Screen() {
		frame = new JFrame();
		setSize(800, 500);
		setTitle("test");
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		ct = getContentPane();
		cl = new CardLayout(0,0);
		ct.setLayout(cl);
		
		game = new Game(0);
		info = new Info();
		join = new Join(this);
		login = new Login(this);
		startbeforelogin = new StartBeforeLogin();
		
		ct.add(game,"Game");
		ct.add(info,"Info");
		ct.add(join,"Join");
		ct.add(login,"Login");
		ct.add(startbeforelogin,"SBL");
		cl.show(ct, "Login");
		login.requestFocus();
		
		setVisible(true);
	}
}


public class mainScreen {
	public static void main(String args[]) {
		new Screen();
	}
}
