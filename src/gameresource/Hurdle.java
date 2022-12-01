package gameresource;

import java.awt.*;
import javax.swing.*;
public class Hurdle {
	// Àå¾Ö¹°
	public JLabel hurdle;
	public Image img;
	public int x,y,w,h;
	
	public Hurdle(ImageIcon himg, int x, int y, int w, int h) {
		hurdle = new JLabel(himg);
		img = himg.getImage();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		hurdle.setBounds(x, y, w, h);
	}
}
