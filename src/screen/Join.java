package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.time.LocalDateTime;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import dbresource.*;
import screen.*;

public class Join extends JPanel {
	
	private JFrame frame;
	private JLabel lblJoin;
	private JButton joinCompleteBtn;
	private JButton checkOverlapBtn; // �ߺ�Ȯ�ο� ��ư
	private JButton backBtn; // �ڷΰ��� ��ư
	
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfPhone;

	private ImageIcon background=new ImageIcon("img/screenimg/StartScreen2.png");
	int xLocation = 230;
	private DBConnection db = new DBConnection(); // ȸ�����Կ� DB

	public Join(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		Font f1 = new Font("CookieRun Bold", Font.BOLD, 15); 
		
		lblJoin = new JLabel("ȸ������");
		lblJoin.setFont(new Font("CookieRun Bold", Font.BOLD, 30)); 
		lblJoin.setForeground(Color.white);
		lblJoin.setBounds(xLocation, 41, 200, 30);
		this.add(lblJoin);

		JLabel lblUsername = new JLabel("username");
		lblUsername.setFont(f1);
		lblUsername.setForeground(Color.white);
		lblUsername.setBounds(xLocation, 113, 150, 20);
		this.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setFont(f1);
		lblPassword.setForeground(Color.white);
		lblPassword.setBounds(xLocation, 163, 150, 20);
		this.add(lblPassword);
		
		
		JLabel lblName = new JLabel("name");
		lblName.setFont(f1);
		lblName.setForeground(Color.white);
		lblName.setBounds(xLocation, 210, 69, 20);
		this.add(lblName);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setFont(f1);
		lblEmail.setForeground(Color.white);
		lblEmail.setBounds(xLocation, 257, 69, 20);
		this.add(lblEmail);
		
		JLabel lblPhone = new JLabel("phone");
		lblPhone.setFont(f1);
		lblPhone.setForeground(Color.white);
		lblPhone.setBounds(xLocation, 304, 69, 20);
		this.add(lblPhone);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(350, 106, 186, 35);
		this.add(tfUsername);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(350, 156, 186, 35);
		this.add(tfPassword);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(350, 203, 186, 35);
		this.add(tfName);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(350, 250, 186, 35);
		this.add(tfEmail);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(350, 297, 186, 35);
		this.add(tfPhone);
		
		joinCompleteBtn = new JButton("ȸ�����ԿϷ�");
		joinCompleteBtn.setFont(f1);
		joinCompleteBtn.setBounds(395, 363, 139, 29);
		joinCompleteBtn.setBackground(new Color(255,204,051));
		joinCompleteBtn.setBorderPainted(false);
		this.add(joinCompleteBtn);
		
		backBtn = new JButton("�ڷ� ����");
		backBtn.setFont(f1);
		backBtn.setBounds(xLocation, 363, 100, 29);
		backBtn.setBackground(new Color(255,204,051));
		backBtn.setBorderPainted(false);
		this.add(backBtn);
		
		checkOverlapBtn = new JButton("ID �ߺ�üũ");
		checkOverlapBtn.setFont(f1);
		checkOverlapBtn.setBounds(550, 107, 120, 29);
		checkOverlapBtn.setBackground(new Color(255,204,051));
		checkOverlapBtn.setBorderPainted(false);
		this.add(checkOverlapBtn);
		
		//ȸ�����ԿϷ� �׼�
		joinCompleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String username = tfUsername.getText();
				String password = tfPassword.getText();
				String name = tfName.getText();
				String email = tfEmail.getText();
				String phone = tfPhone.getText();
				
				LocalDateTime createDate = LocalDateTime.now();
				Member member = new Member(username, password, name, email, phone, createDate);
				db.insertMember(member);
				JOptionPane.showMessageDialog(null, "ȸ�� ������ �Ϸ�Ǿ����ϴ�.");
				//dispose();
				new Login();
			}
		});
		
		// �ߺ� Ȯ��
		checkOverlapBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String findName = tfUsername.getText();
				boolean isUser = db.findUser(findName);
				if(!isUser) { // �ߺ�Ȯ�� ���, �ƹ��� ������ ���� X 
					JOptionPane.showMessageDialog(null, "ID ��� �����մϴ�.");
				}
				else
					JOptionPane.showMessageDialog(null, "�̹� ID�� �����մϴ�.");
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//dispose();
				new Login();
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

