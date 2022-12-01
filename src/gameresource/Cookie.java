package gameresource;

import java.awt.*;
import javax.swing.*;
public class Cookie extends JFrame {
	
	public JLabel cookie; 
	public ImageIcon[] img; // �⺻, ����, ��������, �����̵�, ������, �ǰ� �� 6����
	public int x = 160;
	public int y = 280;
	public int w = 80;
	public int h = 120;
	public int score = 0;
	public int speed = 5;
	public int hp = 1000;
	public int alpha = 255;
	public int jumpcount = 0;
	public int targetheight = 0;
	public boolean invincible = false;
	public boolean fall = false;
	public boolean jump = false;
	public boolean slide = false;
	
	public Cookie(ImageIcon[] img, int speed) {
		
		this.img = img;
		this.speed = speed;
		for (int i=0; i<img.length; i++) { // ��Ű ũ�� ������
			Image tmp = this.img[i].getImage();
			this.img[i] = new ImageIcon(tmp);
		}
		cookie = new JLabel(this.img[0]);
	}
	
	public void jump() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				jump = true;
				jumpcount++;
				int tmp = jumpcount;
				if (jumpcount == 1) {
					targetheight = y - 125;
					cookie.setIcon(img[1]);
				}
				else if (jumpcount == 2) {
					targetheight = y - 125;
					cookie.setIcon(img[2]);
				}
				while (y >= targetheight) { // ��ǥ ���̿� ������ �� ����
					y -= 10;
					cookie.setLocation(x,y);
					if (tmp != jumpcount) // ���� ���¿��� ������ �ѹ� �� �Ǹ� �����ϰ� ���� ������ ����
						break;
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(tmp == jumpcount) {
					targetheight = 0;
					jump = false;
					// fall���� jumpcount 0���� �ʱ�ȭ
				}
			}
		}).start();
	}
	
	public void slideon() {
		if((jumpcount == 0) && (slide == true)) {
			cookie.setIcon(img[3]);
		}
	}
	
	public void slideoff() {
		if((jumpcount == 0) && (slide == false)) {
			cookie.setIcon(img[0]);
		}
	}
}
