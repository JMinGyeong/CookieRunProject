package screen;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import dbresource.*;

public class Ranking extends JPanel {
	Screen screen;
    private static final long serialVersionUID = 1L;
    DBConnection dbConnection = new DBConnection();
    private JTable table;
    private DefaultTableModel model;
    private ImageIcon background=new ImageIcon("img/screenimg/StartScreen2.png");
    public Ranking(Screen screen) {
    	this.screen = screen;
    	this.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(120, 150, 560, 168);
        this.add(scrollPane);

        String[] columnNames = { "rank", "ID", "Score" };
        model = new DefaultTableModel(null, columnNames);
        table = new JTable(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        rankSetting();

        scrollPane.setViewportView(table);


        JButton homeButton = new JButton("Home");
        homeButton.setBounds(350, 350, 100, 40);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(screen.islogin) {
    				screen.cl.show(screen.ct, "SAL");
    				screen.startafterlogin.requestFocus();
                }
                else {
    				screen.cl.show(screen.ct, "SBL");
    				screen.startbeforelogin.requestFocus();
                }
            }
        });
		homeButton.setFont(new Font("CookieRun Bold", Font.BOLD, 20));
		homeButton.setBackground(new Color(255,204,051));
		homeButton.setBorderPainted(false);
        this.add(homeButton);
    }
    public void paintComponent(Graphics g) {
        g.drawImage(background.getImage(), 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
    }

    private void rankSetting(){

        RankInfo[] rankInfos = dbConnection.rankInfo();

        int rankIndex = 1;
        for (RankInfo rankInfo : rankInfos) {
            System.out.println("username : %s, score : %d".formatted(rankInfo.getUsername(), rankInfo.getScore()));
            Object[] temp = {rankIndex++,rankInfo.getUsername(), rankInfo.getScore()};
            model.addRow(temp);
        }

        // 빈칸 처리
        for(int i = ++rankIndex; i <= 10; i++){
            Object[] temp = {i,"정보 없음", "정보 없음"};
            model.addRow(temp);
        }
    }
}
