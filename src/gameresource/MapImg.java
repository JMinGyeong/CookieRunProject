package gameresource;

import java.awt.*;
import javax.swing.*;
public class MapImg {
	
	public JLabel mapimg;
	public Image img;
	public int x,y,w,h;
	
	public MapImg(ImageIcon mimg, int x, int y, int w, int h) {
		mapimg = new JLabel(mimg);
		img = mimg.getImage();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		mapimg.setBounds(x, y, w, h);
	}
}
