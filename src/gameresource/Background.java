package gameresource;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import gameresource.*;
public class Background extends JFrame {
	
	public Cookie c;
	public ImageIcon[] map;
	public MapImg mapimg1, mapimg2;
	public MapObjectImg mapoimg;
	public List<Jelly> jellylist;
	public List<Field> fieldlist;
	public List<Hurdle> hurdlelist;
	public int count, mapcount, distance; // 500마다 초기화되는 거리, 총 이동 거리
	
	public Background(Cookie c, ImageIcon[] map, MapObjectImg mapobjectimg,
			List<Jelly> jellylist,List<Field> fieldlist, List<Hurdle> hurdlelist) {
		this.c = c;
		this.map = map;
		mapimg1 = new MapImg(map[0],0,0,map[0].getIconWidth(),map[0].getIconHeight());
		mapimg2 = new MapImg(map[1],map[0].getIconWidth(),0,map[1].getIconWidth(),map[1].getIconHeight());
		mapoimg = mapobjectimg;
		this.jellylist = jellylist;
		this.fieldlist = fieldlist;
		this.hurdlelist = hurdlelist;
		count = 0;
		mapcount = 0;
		distance = 0;
	}
	
	public void moveBackground() {
		if (count >= 500) {
			c.hp -= 10;
			count = 0;
		}
		count += c.speed;
		mapcount += c.speed;
		distance += c.speed;
		
		if ((mapcount / 20) >= c.speed) { //배경은 느리게 이동
			mapimg1.x -= c.speed;
			mapimg2.x -= c.speed;
			mapcount = 0;
		}

		if (mapimg1.x <= -mapimg1.w) // 배경 반복
			mapimg1.x = mapimg2.w;
		if (mapimg2.x <= -mapimg2.w) 
			mapimg2.x = mapimg1.w;
		
		int face = c.x + c.w;
		int foot = c.y + c.h;
		//발판 이동 겸 fall 검사
		for (int i=0; i<fieldlist.size(); i++) {
			Field temp = fieldlist.get(i);
			if (temp.x<-100)
				fieldlist.remove(i);
			else
				temp.x -= c.speed;
		}
		int now = 2000;
		for (int i = 0; i < fieldlist.size(); i++) {
			Field temp = fieldlist.get(i);
			if (temp.x > c.x - 60 && temp.x <= face) { 
				if (temp.y < now && temp.y >= foot) 
					now = temp.y;
			}
		}
		if (c.y+c.h < now && !c.jump && !c.slide && !c.fall)
			fall(now);
			//발판에 도달할 때까지 fall
		
		//젤리 이동 겸 state(존재 여부, 기존에는 맵에 존재하고 획득하면 맵에서 사라짐) 검사
		for (int i=0; i<jellylist.size(); i++) {
			Jelly temp = jellylist.get(i);
			if (temp.x < -50)
				jellylist.remove(temp);
			temp.x -= c.speed;
			
			if (temp.state == false) {//이미 획득했을 경우
				if (temp.alpha > 0)
					temp.alpha -= 5;
				}
			if ( !c.slide && temp.state
				&& temp.x + temp.w * 20 / 100 >= c.x
				&& temp.x + temp.w * 80 / 100 <= face
				&& temp.y + temp.w * 20 / 100 >= c.y
				&& temp.y + temp.w * 80 / 100 <= foot) {
				if (temp.jelly.getIcon().equals(mapoimg.jellyHPIc)) {
					if ((c.hp + 100) > 1000) 
						c.hp = 1000;
					else 
						c.hp += 100;
				}
				c.score += temp.score;
				temp.jelly.setIcon(mapoimg.jellyEffectIc);
				temp.state = false;
			} else if ( c.slide && temp.state
				&& temp.x + temp.w * 20 / 100 >= c.x
				&& temp.x + temp.w * 80 / 100 <= face
				&& temp.y + temp.w * 20 / 100 >= c.y+ c.h * 1 / 3
				&& temp.y + temp.w * 80 / 100 <= foot) {
				if (temp.jelly.getIcon().equals(mapoimg.jellyHPIc)) {
					if ((c.hp + 100) > 1000) 
						c.hp = 1000;
					else 
						c.hp += 100;
				}
				c.score += temp.score;
				temp.jelly.setIcon(mapoimg.jellyEffectIc);
				temp.state = false;
			}
		}
		//장애물 이동 겸 hit 검사
		for (int i=0; i<hurdlelist.size(); i++) {
			Hurdle temp = hurdlelist.get(i);
			if (temp.x < -100)
				hurdlelist.remove(temp);
			temp.x -= c.speed;
			
			if ( !c.invincible && !c.slide
				&& temp.x + temp.w / 2 >= c.x
				&& temp.x + temp.w / 2 <= face
				&& temp.y + temp.h / 2 >= c.y
				&& temp.y + temp.h / 2 <= foot) 
				hit(); 
			else if (!c.invincible && !c.slide
				&& temp.x + temp.w / 2 >= c.x
				&& temp.x + temp.w / 2 <= face
				&& temp.y <= c.y
				&& temp.y + temp.h * 95 / 100 > c.y) 
				hit(); 
			else if ( !c.invincible && c.slide
				&& temp.x + temp.w / 2 >= c.x
				&& temp.x + temp.w / 2 <= face
				&& temp.y + temp.h / 2 >= c.y	+ c.h * 2 / 3
				&& temp.y + temp.h / 2 <= foot) 
				hit();
			else if ( !c.invincible && c.slide
				&& temp.x + temp.w / 2 >= c.x
				&& temp.x + temp.w / 2 <= face
				&& temp.y < c.y 
				&& temp.y+ temp.h * 95 / 100 > c.y + c.h * 2 / 3) 
				hit(); 
		}
	}
	
	public void fall(int n) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int now = n;
				if (c.y + c.h < now && !c.jump && !c.fall)
					c.fall = true;
					if (c.jumpcount == 2) 
						c.cookie.setIcon(c.img[4]);
					while (c.h + c.y < now) { 
						if (c.y + c.h + 5 >= now) { 
							int temp = now - c.y - c.h;
							c.y += temp;
						}
						else
							c.y += 5;
						for (int i = 0; i < 5; i++) {
							Field temp = fieldlist.get(i);
							if (temp.x > c.x - 60 && temp.x <= c.x+c.w) { 
								if (temp.y < now && temp.y >= c.h+c.y) 
									now = temp.y;
							}
						}
						if (c.jump) 
							break;
						if (c.h + c.y > 800) {
							c.hp = 0;
							break;
						}
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					c.fall = false;
					if (c.slide && !c.jump && !c.fall)
						c.cookie.setIcon(c.img[3]);
					else if (!c.slide && !c.jump && !c.fall)
						c.cookie.setIcon(c.img[0]);
					
					if (!c.jump) 
						c.jumpcount = 0;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void hit() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(c.invincible == false) {
					c.invincible = true;
					c.hp -= 100;
					c.alpha = 100;
					c.cookie.setIcon(c.img[5]);
				}
				try {
					Thread.sleep(1500);
					c.invincible = false;
					c.alpha = 255;
				} catch (InterruptedException e) {
					System.out.println(e);
				}
				if(c.slide == true)
					c.cookie.setIcon(c.img[3]);
				else
					c.cookie.setIcon(c.img[0]);
			}
		}).start();
	}
	
}
