package evaluation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EvaluationDAO {

	private Connection conn;	//db에 접근하는 객체
	private ResultSet rs;
	
	public EvaluationDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword = "123456";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //데이터베이스 오류
	}
	public int write(int bbsID, String userID, int likeit, int badit) {
		String SQL = "INSERT INTO evaluation VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setString(2, userID);
			pstmt.setInt(3, likeit);
			pstmt.setInt(4, badit);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	public ArrayList<Evaluation> whereList(int bbsID, String userID){
		String SQL = "SELECT * FROM evaluation WHERE bbsID = ? AND userID =?"; 
		ArrayList<Evaluation> list = new ArrayList<Evaluation>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setString(2, userID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Evaluation eva = new Evaluation();
				eva.setBbsID(rs.getInt(1));
				eva.setUserID(rs.getString(2));
				eva.setLikeIt(rs.getInt(3));
				eva.setBadIt(rs.getInt(4));
				list.add(eva);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //데이터베이스 오류
	}
	
	public ArrayList<Evaluation> getList(int bbsID){
		String SQL = "SELECT * FROM evaluation WHERE bbsID = ?"; 
		ArrayList<Evaluation> list = new ArrayList<Evaluation>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Evaluation eva = new Evaluation();
				eva.setBbsID(rs.getInt(1));
				eva.setUserID(rs.getString(2));
				eva.setLikeIt(rs.getInt(3));
				eva.setBadIt(rs.getInt(4));
				list.add(eva);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //데이터베이스 오류
	}
	
	public Evaluation getEvaluation(int bbsID) {
		String SQL = "SELECT * FROM evaluation WHERE bbsID = ?";
		ArrayList<Evaluation>list = new ArrayList<Evaluation>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  bbsID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Evaluation eva = new Evaluation();
				eva.setBbsID(rs.getInt(1));
				eva.setUserID(rs.getString(2));
				eva.setLikeIt(rs.getInt(3));
				eva.setBadIt(rs.getInt(4));
				list.add(eva);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int update(int bbsID, String userID,int likeit, int badit) {
		String SQL = "UPDATE evaluation SET likeit=?, badit=? WHERE bbsID = ? AND userID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, likeit);
			pstmt.setInt(2, badit);
			pstmt.setInt(3, bbsID);
			pstmt.setString(4, userID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	public int delete(int bbsID, String userID) {
		String SQL = "DELETE FROM evaluation WHERE bbsID = ? AND userID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setString(2, userID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
}