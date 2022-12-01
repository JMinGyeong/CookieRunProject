package gameresource;

import java.awt.*;
import javax.swing.*;
public class Jelly {
	// Á©¸®
	public JLabel jelly;
	public Image img;
	public int x,y,w,h,score;
	public int alpha = 255;
	public boolean state = true;
	
	public Jelly(ImageIcon jimg, int x, int y, int w, int h, int score) {
		jelly = new JLabel(jimg);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.score = score;
		jelly.setBounds(x,y,w,h);
	}
}
