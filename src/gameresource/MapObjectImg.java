package gameresource;

import java.awt.*;
import javax.swing.*;
public class MapObjectImg {

	public ImageIcon jelly1Ic, jelly2Ic, jelly3Ic, jellyHPIc;
	public ImageIcon jellyEffectIc;
	public ImageIcon field1Ic,field2Ic; 
	public ImageIcon hurdle10Ic, hurdle20Ic, hurdle30Ic; 
	
	public MapObjectImg(ImageIcon j1, ImageIcon j2, ImageIcon j3, 
			ImageIcon jh,ImageIcon je,
			ImageIcon f1, ImageIcon f2,
			ImageIcon h1, ImageIcon h2, ImageIcon h3) {
		jelly1Ic = j1;
		jelly2Ic = j2;
		jelly3Ic = j3;
		jellyHPIc = jh;
		jellyEffectIc = je;
		field1Ic = f1;
		field2Ic = f2;
		hurdle10Ic = h1;
		hurdle20Ic = h2;
		hurdle30Ic = h3;
	}
}
