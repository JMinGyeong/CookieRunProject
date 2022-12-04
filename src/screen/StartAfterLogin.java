package screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import screen.mainScreen;
public class StartAfterLogin extends JPanel {
	Screen screen;
	JLabel back;
	ImageIcon startImg;
	JButton gameBtn, infoBtn, rankingBtn, exitBtn;
	public StartAfterLogin(Screen screen) {
		this.screen = screen;
		this.setLayout(null);
		
		
		startImg = new ImageIcon("img/screenimg/StartScreen.png");
		gameBtn = new JButton("게임시작");
		infoBtn = new JButton("게임설명");
		rankingBtn = new JButton("랭킹");
		exitBtn = new JButton("종료");
		
		gameBtn.setBounds(230, 270, 122, 50);
		infoBtn.setBounds(430, 270, 122, 50);
		rankingBtn.setBounds(230, 340, 122, 50);
		exitBtn.setBounds(430, 340, 122, 50);
		
		gameBtn.setBackground(new Color(255,204,051));
		infoBtn.setBackground(new Color(255,204,051));
		rankingBtn.setBackground(new Color(255,204,051));
		exitBtn.setBackground(new Color(255,204,051));
		gameBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		infoBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		rankingBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		exitBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		gameBtn.setBorderPainted(false);
		infoBtn.setBorderPainted(false);
		rankingBtn.setBorderPainted(false);
		exitBtn.setBorderPainted(false);
		
		gameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				screen.cl.show(screen.ct, "Game");
				screen.game.requestFocus();
				screen.game.start();
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
		
		this.add(gameBtn);
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