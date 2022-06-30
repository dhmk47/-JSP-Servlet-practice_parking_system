package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;
import web.dto.SignupReqUserDto;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService;
	
	public SignupServlet() {
		userService = new UserServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/signup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회원가입 실행");
		
		SignupReqUserDto signupReqUserDto = SignupReqUserDto.builder()
				.name(request.getParameter("name"))
				.username(request.getParameter("username"))
				.password(request.getParameter("password"))
				.email(request.getParameter("email"))
				.build();
		
		try {
			userService.createUser(signupReqUserDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}