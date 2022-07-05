package domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

import domain.entity.User;
import domain.jdbc.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServiceDaoImpl implements ServiceDao{
	private final DBConnectionMgr pool;
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private StringBuilder sb;
	int result;
	
	public int insertUser(User user) throws Exception{
		con = pool.getConnection();
		sb = new StringBuilder();
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		return result;
	}

	@Override
	public User getUser(String username) throws Exception {
		User user = null;
		con = pool.getConnection();
		sb = new StringBuilder();
		
		try {
			sb.append("SELECT\r\n"
					+ "	um.user_code,\r\n"
					+ "	um.name,\r\n"
					+ "	um.email,\r\n"
					+ "	um.username,\r\n"
					+ "	um.password,\r\n"
					+ "	um.create_date,\r\n"
					+ "	um.update_date\r\n"
					+ "FROM\r\n"
					+ "	user_mst um\r\n"
					+ "	LEFT OUTER JOIN user_dtl ud ON(ud.user_code = um.user_code)\r\n"
					+ "WHERE\r\n"
					+ "	username = ?");
			
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = User.builder()
						.user_code(rs.getInt(1))
						.name(rs.getString(2))
						.email(rs.getString(3))
						.username(rs.getString(4))
						.password(rs.getString(5))
						.create_date(rs.getTimestamp(6).toLocalDateTime())
						.update_date(rs.getTimestamp(7).toLocalDateTime())
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return user;
	}

	@Override
	public boolean modifyUser() throws Exception {
		return false;
	}

	@Override
	public boolean removeUser() throws Exception {
		return false;
	}

	@Override
	public boolean insertCar(String car_number) throws Exception {
		con = pool.getConnection();
		sb = new StringBuilder();
		
		try {
			sb.append("INSERT\r\n"
					+ "	car_mst\r\n"
					+ "VALUES(\r\n"
					+ "	0,\r\n"
					+ "	?,\r\n"
					+ "	NOW(),\r\n"
					+ "	NOW()\r\n"
					+ ")");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, car_number);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return result > 0 ? true : false;
	}
}