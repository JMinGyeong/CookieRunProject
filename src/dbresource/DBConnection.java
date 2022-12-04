package dbresource;

import java.sql.*;
import java.util.ArrayList;

import dbresource.Member;
import dbresource.RankInfo;

public class DBConnection {
	
	static Connection conn=null;
	Statement stmt;
	ResultSet result;
	private PreparedStatement pstmt; //Query 작성 객체
	String driver;
	String url;
	String id;
	String pw;
	public DBConnection() {
		conn =null;
		url="jdbc:mysql://localhost:3306/cookie?serverTimezone=UTC";
		id="root";
		pw="root";
		driver="com.mysql.cj.jdbc.Driver";
		
//		return conn;
	}
	
	public void link() {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, id, pw);
			
			System.out.println("DB연결 완료");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB연결 실패");
		}
	}
	
	public boolean findUser(String findName) {
		link();
		
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement("select * from member where username = ?");
			pstmt.setString(1, findName);
			result = pstmt.executeQuery();
		
			if(result.next()) { //next()함수는 커서를 한칸 내리면서 해당 행에 데이터가 있으면 true, 없으면 false 반환
				//결과가 있다는 것은 해당 아이디와 비번에 매칭되는 값이 있다는 뜻.
				flag = true;//로그인 성공
			}	
			result.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public void insertMember(Member member) {
		link();
		
		boolean flag = false;
		try {
			String SQL = "insert into member(username, password, name, email, phone, createDate, score) values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, member.getUsername());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setTimestamp(6, Timestamp.valueOf(member.getCreateDate()));
			pstmt.setInt(7, member.getScore());
			int r = pstmt.executeUpdate();
		
			pstmt.close();
			conn.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public RankInfo[] rankInfo(){
			link();
			ArrayList<RankInfo> list = new ArrayList<>();

			try {
				pstmt = conn.prepareStatement("select username, score from member order by score desc limit 10");

				result = pstmt.executeQuery();

				int rowIndex = 0;

				while (result.next()) { 
					String username = result.getString("username");
					int score = result.getInt("score");
					System.out.println("username = " + username);
					System.out.println("score = " + score);
					list.add(new RankInfo(username, score));
					rowIndex++;

				}

				result.close();
				conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RankInfo[] arr = list.stream()
					.toArray(RankInfo[]::new);


			return arr;
		}

	
	public boolean login(String username, String password) {
		link();
		
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement("select * from member where username = ? and password = ?");
			pstmt.setString(1, username);
			pstmt.setString(2,  password);
			result = pstmt.executeQuery();
			if(result.next()) { //next()함수는 커서를 한칸 내리면서 해당 행에 데이터가 있으면 true, 없으면 false 반환
				//결과가 있다는 것은 해당 아이디와 비번에 매칭되는 값이 있다는 뜻.
				flag = true;//로그인 성공
			}	
			result.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateScore(String username, int score) {
		link();
		
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement("update member set score = ? where username = ?");
			pstmt.setInt(1, score);
			pstmt.setString(2, username);
			int r = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
