package domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.entity.Car;
import domain.entity.CarAllInfo;
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
					+ "	um.password\r\n"
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
	public boolean modifyUser(User user, int user_code) throws Exception {
		con = pool.getConnection();
		sb = new StringBuilder();
		
		try {
			sb.append("UPDATE\r\n"
					+ "	user_mst\r\n"
					+ "SET\r\n"
					+ "	NAME = ?,\r\n"
					+ "	email = ?,\r\n"
					+ "	PASSWORD = ?,\r\n"
					+ "	update_date = NOW()\r\n"
					+ "WHERE\r\n"
					+ "	user_code = ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setInt(4, user_code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		return result != 0;
	}
	
	@Override
	public boolean insertUserDtl(int user_code, int car_code) throws Exception {
		con = pool.getConnection();
		sb = new StringBuilder();
		
		try {
			sb.append("INSERT INTO\r\n"
					+ "	user_dtl\r\n"
					+ "VALUES(\r\n"
					+ "	?,\r\n"
					+ "	?,\r\n"
					+ "	NOW(),\r\n"
					+ "	NOW()\r\n"
					+ ")");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, user_code);
			pstmt.setInt(2, car_code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		return result != 0;
	}

	@Override
	public boolean removeUser() throws Exception {
		return false;
	}

	@Override
	public int insertCar(String car_number, int ticket_code, int year, int dayOfYear, int hour) throws Exception {
		con = pool.getConnection();
		sb = new StringBuilder();
		
		try {
			sb.append("INSERT INTO\r\n"
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
			
			sb = new StringBuilder();
			
			sb.append("UPDATE\r\n"
					+ "	car_dtl\r\n"
					+ "SET\r\n"
					+ "	ticket_code = ?,\r\n"
					+ "	start_year = ?,\r\n"
					+ "	start_day = ?,\r\n"
					+ "	start_hour = ?\r\n"
					+ "WHERE\r\n"
					+ "	car_code = (select\r\n"
					+ "						car_code\r\n"
					+ "					from\r\n"
					+ "						car_mst\r\n"
					+ "					where\r\n"
					+ "						car_number = ?)");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, ticket_code);
			pstmt.setInt(2, year);
			pstmt.setInt(3, dayOfYear);
			pstmt.setInt(4, hour);
			pstmt.setString(5, car_number);
			result += pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return result;
	}
	
	@Override
	public CarAllInfo getCarInfoByCarNumber(String car_number) throws Exception {
		CarAllInfo car = null;
		sb = new StringBuilder();
		
		con = pool.getConnection();
		
		try {
			sb.append("SELECT\r\n"
					+ "	cm.car_code,\r\n"
					+ "	cm.car_number,\r\n"
					+ "	pt.ticket_dtl,\r\n"
					+ "	cd.start_year,\r\n"
					+ "	cd.start_day,\r\n"
					+ "	cd.start_hour\r\n"
					+ "FROM\r\n"
					+ "	car_mst cm\r\n"
					+ "	LEFT OUTER JOIN car_dtl cd ON(cd.car_code = cm.car_code)\r\n"
					+ "	LEFT OUTER JOIN parking_ticket pt ON(pt.ticket_code = cd.ticket_code)\r\n"
					+ "WHERE\r\n"
					+ "	cm.car_number = ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, car_number);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				car = CarAllInfo.builder()
						.car_code(rs.getInt(1))
						.car_number(rs.getString(2))
						.ticket_dtl(rs.getString(3))
						.start_year(rs.getInt(4))
						.start_dayOfYear(rs.getInt(5))
						.start_hour(rs.getInt(6))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return car;
	}
	
	@Override
	public ArrayList<CarAllInfo> getCarInfoByCarCode(int car_code) throws Exception {
		con = pool.getConnection();
		sb = new StringBuilder();
		ArrayList<CarAllInfo> carList = new ArrayList<CarAllInfo>();
		
		try {
			sb.append("SELECT\r\n"
					+ "	ud.car_code,\r\n"
					+ "	cm.car_number,\r\n"
					+ "	pt.ticket_dtl,\r\n"
					+ "	cd.start_year,\r\n"
					+ "	cd.start_day,\r\n"
					+ "	cd.start_hour\r\n"
					+ "FROM\r\n"
					+ "	user_dtl ud\r\n"
					+ "	LEFT OUTER JOIN car_mst cm ON(cm.car_code = ud.car_code)\r\n"
					+ "	LEFT OUTER JOIN car_dtl cd ON(cd.car_code = cm.car_code)\r\n"
					+ "	LEFT OUTER JOIN parking_ticket pt ON(pt.ticket_code = cd.ticket_code)\r\n"
					+ "WHERE\r\n"
					+ "	ud.user_code = ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, car_code);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CarAllInfo carInfo = CarAllInfo.builder()
						.car_code(rs.getInt(1))
						.car_number(rs.getString(2))
						.ticket_dtl(rs.getString(3))
						.start_year(rs.getInt(4))
						.start_dayOfYear(rs.getInt(5))
						.start_hour(rs.getInt(6))
						.build();
				
				carList.add(carInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return carList;
	}
	
	/*@Override
	public boolean insertCarDtl(int car_code, int ticket_code) throws Exception {
		con = pool.getConnection();
		sb = new StringBuilder();
		
		try {
			sb.append("UPDATE\r\n"
					+ "	car_dtl\r\n"
					+ "SET\r\n"
					+ "	ticket_code = ?\r\n"
					+ "WHERE\r\n"
					+ "	car_code = ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, ticket_code);
			pstmt.setInt(2, car_code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		return result != 0;		// 0이 아니면 true를 반환 -> update 성공을 의미
	}*/
}