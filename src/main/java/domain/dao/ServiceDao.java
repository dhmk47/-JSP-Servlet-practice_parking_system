package domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import domain.entity.User;
import domain.jdbc.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServiceDao {
	private final DBConnectionMgr pool;
	
	public int insertUser(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		int result = 0;
		try {
			con = pool.getConnection();
			sb.append("INSERT INTO\r\n"
					+ "	user_mst\r\n"
					+ "VALUES(\r\n"
					+ "	0,\r\n"
					+ "	?,\r\n"
					+ "	?,\r\n"
					+ "	?,\r\n"
					+ "	?,\r\n"
					+ "	NOW(),\r\n"
					+ "	NOW()\r\n"
					+ ");");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		return result;
	}
}