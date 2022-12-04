package screen;

import java.awt.*;
import javax.swing.*;
import dbresource.*;
import gameresource.*;
import screen.*;

class Screen extends JFrame {
	Game game;
	GameOver gameover;
	Info info;
	Join join;
	Login login;
	Ranking rank;
	StartBeforeLogin startbeforelogin; // 로그인 하기 전 시작 화면
	StartAfterLogin startafterlogin; // 로그인 후 시작 화면
	public String username = null;
	public int score = 0;
	public Container ct;
	public CardLayout cl;
	public boolean islogin = false;
	
	public Screen() {
		setSize(800, 500);
		setTitle("test");
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		ct = getContentPane();
		cl = new CardLayout(0,0);
		ct.setLayout(cl);
		
		game = new Game(this,0);
		info = new Info(this);
		join = new Join(this);
		login = new Login(this);
		rank = new Ranking(this);
		startbeforelogin = new StartBeforeLogin(this);
		startafterlogin = new StartAfterLogin(this);
		
		ct.add(game,"Game");
		ct.add(info,"Info");
		ct.add(join,"Join");
		ct.add(login,"Login");
		ct.add(rank,"Ranking");
		ct.add(startbeforelogin,"SBL");
		ct.add(startafterlogin,"SAL");
		cl.show(ct, "SBL");
		startbeforelogin.requestFocus();
		
		setVisible(true);
	}
}


public class mainScreen {
	public static void main(String args[]) {
		new Screen();
	}
}
