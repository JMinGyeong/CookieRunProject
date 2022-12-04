package screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import dbresource.DBConnection;
import screen.Ranking;
public class GameOver extends JPanel{
	Screen screen;
	private ImageIcon image = new ImageIcon("img/screenimg/OverScreen.png");
	private JPanel contentPane;
	private JLabel jLabel;
	private DBConnection dbConnection = new DBConnection();

	public GameOver(Screen screen) {
		this.screen = screen;
		this.setLayout(null);
		jLabel = new JLabel(String.valueOf(screen.score));

		JButton ranking = new JButton("Ranking");
		ranking.setBounds(335, 390, 122, 50);
		ranking.setBackground(new Color(255, 204,051));
		ranking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				screen.cl.show(screen.ct, "Ranking");
				screen.rank.requestFocus();
			}
		});
		
		
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		jLabel.setForeground(Color.WHITE);
		jLabel.setFont(new Font("Calibri", Font.BOLD, 100));
		jLabel.setBounds(0,250,800,100);
		
		this.add(ranking);
		this.add(jLabel);

		System.out.println("username = " + screen.username);
		dbConnection.updateScore(screen.username, screen.score);
		screen.ct.remove(screen.rank);
		screen.rank = new Ranking(screen);
		screen.ct.add(screen.rank,"Ranking");
		screen.cl.show(screen.ct, "Ranking");
		screen.startafterlogin.requestFocus();
	}
	public void paintComponent(Graphics g) {
		g.drawImage(image.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}