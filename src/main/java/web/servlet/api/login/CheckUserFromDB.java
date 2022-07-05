package web.servlet.api.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.dao.ServiceDaoImpl;
import domain.entity.User;
import domain.jdbc.DBConnectionMgr;
import service.UserService;
import service.UserServiceImpl;

@WebServlet("/login")
public class CheckUserFromDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService;
	
	public CheckUserFromDB() {
		userService = new UserServiceImpl(new ServiceDaoImpl(DBConnectionMgr.getInstance()));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		try {
			User user = userService.getUser(username);
			int result = 0;
			if(user == null) {
				result = 0;
			}else if(user.getPassword().equals(password)) {
				result = 2;
			}else {
				result = 1;
			}
			// result: 0 -> 아이디 없음
			// result: 1 -> 비밀번호 틀림
			// result: 2 -> 계정 로그인 성공
			response.getWriter().print(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
