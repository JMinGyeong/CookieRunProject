package screen;

import java.awt.*;
import javax.swing.*;

public class StartBeforeLogin extends JPanel {
	JLabel back;
	ImageIcon startImg;
	JButton loginBtn, joinBtn, rankingBtn, exitBtn;
	public StartBeforeLogin() {
		this.setLayout(null);
		
		startImg = new ImageIcon("img/screenimg/StartScreen.png");
		loginBtn = new JButton("로그인");
		joinBtn = new JButton("게임설명");
		rankingBtn = new JButton("랭킹");
		exitBtn = new JButton("종료");
		
		loginBtn.setBounds(230, 270, 122, 50);
		joinBtn.setBounds(430, 270, 122, 50);
		rankingBtn.setBounds(230, 340, 122, 50);
		exitBtn.setBounds(430, 340, 122, 50);
		
		loginBtn.setBackground(new Color(255,204,051));
		joinBtn.setBackground(new Color(255,204,051));
		rankingBtn.setBackground(new Color(255,204,051));
		exitBtn.setBackground(new Color(255,204,051));
		loginBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		joinBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		rankingBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		exitBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		loginBtn.setBorderPainted(false);
		joinBtn.setBorderPainted(false);
		rankingBtn.setBorderPainted(false);
		exitBtn.setBorderPainted(false);
		
		this.add(loginBtn);
		this.add(joinBtn);
		this.add(rankingBtn);
		this.add(exitBtn);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(startImg.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
