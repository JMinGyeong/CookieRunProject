package screen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import dbresource.*;
import screen.*;

public class Login extends JPanel {
	
	private Screen screen;
	private JTextField tfUsername, tfPassword;
	private JButton loginBtn, joinBtn;
	private ImageIcon background=new ImageIcon("img/screenimg/StartScreen2.png");
	private DBConnection db = new DBConnection(); // ȸ�����Կ� DB
	
	public Login(Screen screen) {
		this.screen = screen;
		this.setLayout(null);
		
		JLabel lblTitle = new JLabel("�α���");
		lblTitle.setFont(new Font("CookieRun Bold", Font.BOLD, 30)); 
		lblTitle.setForeground(Color.white);
		lblTitle.setBounds(250, 120, 200, 30);
		this.add(lblTitle);
		
		JLabel lblLogin = new JLabel("���̵�");
		lblLogin.setBounds(250, 175, 100, 50);
		lblLogin.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		lblLogin.setForeground(Color.WHITE);
		this.add(lblLogin);
		
		JLabel lblPassword = new JLabel("��й�ȣ");
		lblPassword.setBounds(250, 225, 100, 50);
		lblPassword.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		lblPassword.setForeground(Color.WHITE);
		this.add(lblPassword);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(350, 180, 176, 35);
		this.add(tfUsername);
		tfUsername.setColumns(10);
		
		loginBtn = new JButton("�α���");
		loginBtn.setBounds(250, 300, 120, 40);
		loginBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		loginBtn.setBackground(new Color(255,204,051));
		loginBtn.setBorderPainted(false);
		this.add(loginBtn);
		
		joinBtn = new JButton("ȸ������");
		joinBtn.setBounds(405, 300, 120, 40);
		joinBtn.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		joinBtn.setBackground(new Color(255,204,051));
		joinBtn.setBorderPainted(false);
		this.add(joinBtn);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(350, 230, 176, 35);
		this.add(tfPassword);
		
		//ȸ������ �׼�
		joinBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				screen.cl.show(screen.ct, "Join");
			}
		});
		
		//�α��� �׼�
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	
				String id = tfUsername.getText();
				String pwd = tfPassword.getText();
				boolean isLogined = db.login(id, pwd);
	
				String result = (isLogined) ? "�α��� ����" : "�α��� ����";
				JOptionPane.showMessageDialog(null, result);
				if (isLogined) {
					screen.username = id;
					screen.islogin = true;
					screen.startafterlogin = new StartAfterLogin(screen);
					screen.ct.add(screen.startafterlogin,"SAL");
					screen.cl.show(screen.ct, "SAL");
					screen.startafterlogin.requestFocus();
				}
			}
		});
	}
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}

