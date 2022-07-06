package web.servlet.api.auth;

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

@WebServlet("/username/check")
public class UsernameCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService;
	
	public UsernameCheckServlet() {
		userService = new UserServiceImpl(new ServiceDaoImpl(DBConnectionMgr.getInstance()));
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		String username = request.getParameter("username");
		response.setContentType("text/plain;charset=UTF-8");
		
		try {
			user = userService.getUser(username);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(user != null) {
				response.getWriter().print(true);
			}else {
				response.getWriter().print(false);
			}
		}
		
	}

}
