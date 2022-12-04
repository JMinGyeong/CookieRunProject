package screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import screen.mainScreen;
public class Info extends JPanel {
	Screen screen;
	ImageIcon infoImg;
	JButton infoExit;
	public Info(Screen screen) {
		this.screen = screen;
		this.setLayout(null);
		infoImg = new ImageIcon("img/screenimg/infoscreen.png");
		infoExit = new JButton("X");
		infoExit.setBounds(700,390,50,50);
		infoExit.setBackground(new Color(255,204,051));
		infoExit.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		infoExit.setBorderPainted(false);
		infoExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(screen.islogin) {
					screen.cl.show(screen.ct, "SAL");
					screen.startafterlogin.requestFocus();
				}
				else {
					screen.cl.show(screen.ct, "SBL");
					screen.startbeforelogin.requestFocus();
				}
			}
		});
		this.add(infoExit);
		}
	@Override
	public void paint(Graphics g) {
		g.drawImage(infoImg.getImage(), 0, 0, null);
	}
}
