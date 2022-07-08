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
import web.dto.ReqUserDto;

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
		if(!request.getParameter("selectEmail").equals("직접입력")) {
			email = request.getParameter("email") + request.getParameter("selectEmail");
		}else {
			email = request.getParameter("email");
		}
		
		ReqUserDto signupReqUserDto = ReqUserDto.builder()
				.name(request.getParameter("name"))
				.username(request.getParameter("username"))
				.password(request.getParameter("password"))
				.email(email)
				.build();
		
		try {
			if(userService.createUser(signupReqUserDto)) {
				response.getWriter().print(true);
//				response.sendRedirect("/root/index"); // requset.getParameter 값을 가져올수 없습니다.
			}else {
				response.getWriter().print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}