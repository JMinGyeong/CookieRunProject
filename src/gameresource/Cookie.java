package gameresource;

import java.awt.*;
import javax.swing.*;
public class Cookie extends JFrame {
	
	public JLabel cookie; 
	public ImageIcon[] img; // 기본, 점프, 더블점프, 슬라이드, 떨어짐, 피격 총 6가지
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
		for (int i=0; i<img.length; i++) { // 쿠키 크기 조정용
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
				while (y >= targetheight) { // 목표 높이에 도달할 때 까지
					y -= 10;
					cookie.setLocation(x,y);
					if (tmp != jumpcount) // 점프 상태에서 점프가 한번 더 되면 종료하고 다음 스레드 실행
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
					// fall에서 jumpcount 0으로 초기화
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
