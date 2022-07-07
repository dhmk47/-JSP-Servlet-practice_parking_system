package web.servlet.api.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

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
		
		response.setContentType("text/plain;charset=UTF-8");
		User user = null;
		
		try {
			user = userService.getUser(username);
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
			}
			response.getWriter().print(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
