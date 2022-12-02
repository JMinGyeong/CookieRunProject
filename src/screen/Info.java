package screen;

import java.awt.*;
import javax.swing.*;
public class Info extends JPanel {
	ImageIcon infoImg;
	JButton infoExit;
	public Info() {
		this.setLayout(null);
		infoImg = new ImageIcon("img/screenimg/infoscreen.png");
		infoExit = new JButton("X");
		infoExit.setBounds(700,390,50,50);
		infoExit.setBackground(new Color(255,204,051));
		infoExit.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		infoExit.setBorderPainted(false);
		this.add(infoExit);
		}
	@Override
	public void paint(Graphics g) {
		g.drawImage(infoImg.getImage(), 0, 0, null);
	}
}
