package gameresource;

import java.awt.*;
import javax.swing.*;
public class Field {
	// ¹ßÆÇ
	public JLabel field;
	public Image img;
	public int x,y,w,h;
	
	public Field (ImageIcon fimg, int x, int y, int w, int h) {
		field = new JLabel(fimg);
		img = fimg.getImage();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		field.setBounds(x, y, w, h);
	}
}
