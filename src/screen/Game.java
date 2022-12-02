package screen;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import gameresource.*;
public class Game extends JPanel implements KeyListener {
	public JPanel game;
	public Cookie c;
	public Background b;
	public ImageIcon jumpUp, jumpDown, slideUp, slideDown;
	public JLabel jumpBtn, slideBtn;
	public java.util.List<Jelly> jellylist;
	public java.util.List<Field> fieldlist;
	public java.util.List<Hurdle> hurdlelist; 
	Image buffImg;
	Graphics buffg;
	AlphaComposite alphaComposite;
	
	
	public Game(int i) { 
		game = new JPanel();
		game.setLayout(null);
		
		ImageIcon[] cimg = new ImageIcon[6];
		int cspeed = 0;
		//select한 쿠키 번호에 따라 cimg를 다르게 선택
		if (i == 0) { //기본, 점프, 더블점프, 슬라이드, 떨어짐, 피격 총 6가지
			cimg[0] = new ImageIcon("img/cookieimg/cookie1/normal.gif");
			cimg[1] = new ImageIcon("img/cookieimg/cookie1/jump.gif");
			cimg[2] = new ImageIcon("img/cookieimg/cookie1/doublejump.gif");
			cimg[3] = new ImageIcon("img/cookieimg/cookie1/slide.gif");
			cimg[4] = new ImageIcon("img/cookieimg/cookie1/fall.png");
			cimg[5] = new ImageIcon("img/cookieimg/cookie1/hit.png");
			cspeed = 5;
		}
		else if (i == 1) {
			cimg[0] = new ImageIcon("img/cookieimg/cookie2/normal.gif");
			cimg[1] = new ImageIcon("img/cookieimg/cookie2/jump.gif");
			cimg[2] = new ImageIcon("img/cookieimg/cookie2/doublejump.gif");
			cimg[3] = new ImageIcon("img/cookieimg/cookie2/slide.gif");
			cimg[4] = new ImageIcon("img/cookieimg/cookie2/fall.png");
			cimg[5] = new ImageIcon("img/cookieimg/cookie2/hit.gif");
			cspeed = 5;
		}
		else {
			cimg[0] = new ImageIcon("img/cookieimg/cookie3/normal.gif");
			cimg[1] = new ImageIcon("img/cookieimg/cookie3/jump.png");
			cimg[2] = new ImageIcon("img/cookieimg/cookie3/doublejump.gif");
			cimg[3] = new ImageIcon("img/cookieimg/cookie3/slide.gif");
			cimg[4] = new ImageIcon("img/cookieimg/cookie3/fall.png");
			cimg[5] = new ImageIcon("img/cookieimg/cookie3/hit.png");
			cspeed = 5;
		}
		c = new Cookie(cimg, cspeed);
		
		ImageIcon[] map = new ImageIcon[2];
		map[0] = new ImageIcon("img/game/stageimg.jpg");
		map[1] = new ImageIcon("img/game/stageimg.jpg");
		

		MapObjectImg mapoimg;
		mapoimg = new MapObjectImg(new ImageIcon("img/game/jelly1.png"),
				new ImageIcon("img/game/jelly2.png"),
				new ImageIcon("img/game/jelly3.png"),
				new ImageIcon("img/game/hppotion.png"),
				new ImageIcon("img/game/effectTest.png"),
				new ImageIcon("img/game/field1.png"),
				new ImageIcon("img/game/field2.png"),
				new ImageIcon("img/game/tacle1.png"),
				new ImageIcon("img/game/tacle2.png"),
				new ImageIcon("img/game/tacle3.png"));

		
		jellylist = new java.util.ArrayList<Jelly>();
		fieldlist = new java.util.ArrayList<Field>();;
		hurdlelist = new java.util.ArrayList<Hurdle>();;
		
		int[] size;
		int[][] color;
		int mapLength = 0;
		
		for (int j=1; j<4; j++) {
			String tempMap = "img/map/map"+Integer.toString(j)+".png";
			try {
				size = getSize(tempMap);
				color = getPic(tempMap);
				
				int maxX = size[0]; 
				int maxY = size[1];

				for (int h = 0; h < maxX; h += 1) { 
					for (int k = 0; k < maxY; k += 1) {
						if (color[h][k] == 16776960) { // 색값이 16776960일 경우 젤리1
							jellylist.add(new Jelly(mapoimg.jelly1Ic, h * 40 + mapLength * 40, k * 40, 30, 30, 500));

						} else if (color[h][k] == 13158400) { // 색값이 13158400일 경우 젤리2
							jellylist.add(new Jelly(mapoimg.jelly2Ic, h * 40 + mapLength * 40, k * 40, 30, 30, 2000));

						} else if (color[h][k] == 9868800) { // 색값이 9868800일 경우 젤리3
							jellylist.add(new Jelly(mapoimg.jelly3Ic, h * 40 + mapLength * 40, k * 40, 30, 30, 3000));

						} else if (color[h][k] == 16737280) { // 색값이 16737280일 경우 생명물약
							jellylist.add(new Jelly(mapoimg.jellyHPIc, h * 40 + mapLength * 40, k * 40, 30, 30, 0));
						}
					}
				}

				for (int h = 0; h < maxX; h += 2) { 
					for (int k = 0; k < maxY; k += 2) {
						if (color[h][k] == 0) { // 색값이 0 일경우 (검은색)
							fieldlist.add(new Field(mapoimg.field1Ic, h * 40 + mapLength * 40, k * 40, 80, 80));

						} else if (color[h][k] == 6579300) { // 색값이 6579300 일경우 (회색)
							fieldlist.add(new Field(mapoimg.field2Ic, h * 40 + mapLength * 40, k * 40, 80, 80));
						}
					}
				}

				for (int h = 0; h < maxX; h += 2) {
					for (int k = 0; k < maxY; k += 2) {
						if (color[h][k] == 16711680) { // 색값이 16711680일 경우 (빨간색) 1칸
							hurdlelist.add(new Hurdle(mapoimg.hurdle10Ic, h * 40 + mapLength * 40, k * 40, 80, 80));

						} else if (color[h][k] == 16711830) { // 색값이 16711830일 경우 (분홍) 2칸
							hurdlelist.add(new Hurdle(mapoimg.hurdle20Ic, h * 40 + mapLength * 40, k * 40, 80, 160));

						} else if (color[h][k] == 16711935) { // 색값이 16711830일 경우 (핫핑크) 3칸
							hurdlelist.add(new Hurdle(mapoimg.hurdle30Ic, h * 40 + mapLength * 40, k * 40, 80, 240));
						}
					}
				}
				mapLength += size[0];
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		b = new Background(c, map, mapoimg, jellylist, fieldlist, hurdlelist);

		jumpUp = new ImageIcon("img/game/jumpno.png");
		jumpDown = new ImageIcon("img/game/jumpdim.png");
		slideUp = new ImageIcon("img/game/slideno.png");
		slideDown = new ImageIcon("img/game/slidedim.png");
		jumpBtn = new JLabel(jumpUp);
		slideBtn = new JLabel(slideUp);
		
	}
	
	public void start() {

		this.setFocusable(true);
		this.addKeyListener(this);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					b.moveBackground();
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException ie) {
						System.out.println(ie);
					}
				}
			}
		}).start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {

		if (buffg == null) {
			buffImg = createImage(this.getWidth(), this.getHeight());
			if (buffImg == null) {
				System.out.println("더블 버퍼링용 오프 스크린 생성 실패");
			} else {
				buffg = buffImg.getGraphics();
			}
		}

		Graphics2D g2 = (Graphics2D) buffg;
		super.paintComponent(buffg);

		buffg.drawImage(b.mapimg1.img, b.mapimg1.x, 0, b.mapimg1.w, b.mapimg1.h * 5 / 4, null);
		buffg.drawImage(b.mapimg2.img, b.mapimg2.x, 0, b.mapimg2.w, b.mapimg2.h * 5 / 4, null);
			
		for (int i = 0; i < b.fieldlist.size(); i++) {
			Field tempFoot = b.fieldlist.get(i);
			if (tempFoot.x > -90 && tempFoot.x < 810) { 
				buffg.drawImage(tempFoot.img, tempFoot.x, tempFoot.y, tempFoot.w,
						tempFoot.h, null);
			}
		}

			
		for (int i = 0; i < b.jellylist.size(); i++) {
			Jelly tempJelly = b.jellylist.get(i);
			Image temp = ((ImageIcon)tempJelly.jelly.getIcon()).getImage();
			if (tempJelly.x > -90 && tempJelly.x < 810) {
				alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
						(float) tempJelly.alpha / 255);
				g2.setComposite(alphaComposite); 
				buffg.drawImage(temp, tempJelly.x, tempJelly.y, tempJelly.w,
						tempJelly.h, null);
				alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
				g2.setComposite(alphaComposite);
				}
			}

		for (int i = 0; i < b.hurdlelist.size(); i++) {
			Hurdle tempHurdle = b.hurdlelist.get(i);
			if (tempHurdle.x > -90 && tempHurdle.x < 810) {
				buffg.drawImage(tempHurdle.img, tempHurdle.x, tempHurdle.y, tempHurdle.w,
					tempHurdle.h, null);
			}
		}
		ImageIcon temp = (ImageIcon)c.cookie.getIcon();
		if (c.invincible) { 
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) c.alpha / 255);
			g2.setComposite(alphaComposite);
			buffg.drawImage(temp.getImage(), c.x-110 , c.y-170 ,
					temp.getIconWidth()*8/10 , temp.getIconHeight() *8/10, null);
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2.setComposite(alphaComposite);
		} else { 
			buffg.drawImage(temp.getImage(), c.x -110, c.y-170 ,
				temp.getIconWidth()*8/10 , temp.getIconHeight() *8/10, null);
		} 
		
		//점수 표시
		buffg.setFont(new Font("CookieRun Bold", Font.BOLD, 30));
		buffg.setColor(Color.WHITE);
		buffg.drawString(Integer.toString(c.score), 350, 60);
		//체력 표시 바 위치 나중에 수정!!
		buffg.setColor(Color.RED);
		buffg.fillRect(150, 20, (int)(470 * ((double)c.hp/1000)), 15);
		buffg.drawImage(((ImageIcon)jumpBtn.getIcon()).getImage(), 10, 360, 132, 100, null);
		buffg.drawImage(((ImageIcon)slideBtn.getIcon()).getImage(), 640, 360, 132, 100, null);

		g.drawImage(buffImg, 0, 0, this);

	}
	@Override
	public void keyTyped(KeyEvent ke) {
		
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_UP) {
			jumpBtn.setIcon(jumpDown);
			if(c.jumpcount < 2)
				c.jump();
		}
		else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {
			slideBtn.setIcon(slideDown);
			c.slide = true;
			c.slideon();
		}
	}
	@Override
	public void keyReleased(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_UP) {
			jumpBtn.setIcon(jumpUp);
		}
		else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
			slideBtn.setIcon(slideUp);
			c.slide = false;
			c.slideoff();
		}
		
	}
	
	public int[] getSize(String src) {
		try {
			File imgf = new File(src);
			BufferedImage img = ImageIO.read(imgf);
			int width = img.getWidth();
			int height = img.getHeight();
			int[] tempSize = {width, height};
			return tempSize;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int[][] getPic(String src) {
		try {
			File imgf = new File(src);
			BufferedImage img = ImageIO.read(imgf);
			int width = img.getWidth();
			int height = img.getHeight();
			int[] pixels=new int[width*height];
			PixelGrabber grab = new PixelGrabber(img, 0, 0, width, height, pixels, 0,width);
			grab.grabPixels();
			
			int[][] picture=new int[width][height];
			for(int i=0;i<pixels.length;i++)
			      picture[i%width][i/width]=pixels[i] + 16777216;
			return picture;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
