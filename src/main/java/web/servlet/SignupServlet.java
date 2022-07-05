package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.dao.ServiceDaoImpl;
import domain.jdbc.DBConnectionMgr;
import service.UserService;
import service.UserServiceImpl;
import web.dto.SignupReqUserDto;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService;
	
	public SignupServlet() {
		userService = new UserServiceImpl(new ServiceDaoImpl(DBConnectionMgr.getInstance()));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/signup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회원가입 실행");
		String email = null;
		if(!request.getParameter("select-email").equals("직접입력")) {
			email = request.getParameter("email") + request.getParameter("select-email");
		}else {
			email = request.getParameter("email");
		}
		
		SignupReqUserDto signupReqUserDto = SignupReqUserDto.builder()
				.name(request.getParameter("name"))
				.username(request.getParameter("username"))
				.password(request.getParameter("password"))
				.email(email)
				.build();
		
		try {
			userService.createUser(signupReqUserDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}