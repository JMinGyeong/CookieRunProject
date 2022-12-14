package screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import screen.mainScreen;
public class StartBeforeLogin extends JPanel {
	Screen screen;
	JLabel back;
	ImageIcon startImg;
	JButton loginBtn, infoBtn, rankingBtn, exitBtn;
	public StartBeforeLogin(Screen screen) {
		this.screen = screen;
		this.setLayout(null);
		
		startImg = new ImageIcon("img/screenimg/StartScreen.png");
		loginBtn = new JButton("로그인");
		infoBtn = new JButton("게임설명");
		rankingBtn = new JButton("랭킹");
		exitBtn = new JButton("종료");
		
		loginBtn.setBounds(230, 270, 122, 50);
		infoBtn.setBounds(430, 270, 122, 50);
		rankingBtn.setBounds(230, 340, 122, 50);
		exitBtn.setBounds(430, 340, 122, 50);
		
		loginBtn.setBackground(new Color(255,204,051));
		infoBtn.setBackground(new Color(255,204,051));
		rankingBtn.setBackground(new Color(255,204,051));
		exitBtn.setBackground(new Color(255,204,051));
		loginBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		infoBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		rankingBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		exitBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		loginBtn.setBorderPainted(false);
		infoBtn.setBorderPainted(false);
		rankingBtn.setBorderPainted(false);
		exitBtn.setBorderPainted(false);
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				screen.cl.show(screen.ct, "Login");
				screen.login.requestFocus();
			}
		});
		infoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				screen.cl.show(screen.ct, "Info");
				screen.info.requestFocus();
			}
		});
		rankingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				screen.cl.show(screen.ct, "Ranking");
				screen.rank.requestFocus();
			}
		});
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				screen.dispose();
			}
		});
		
		this.add(loginBtn);
		this.add(infoBtn);
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
