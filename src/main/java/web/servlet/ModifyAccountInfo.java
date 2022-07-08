package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.dao.ServiceDaoImpl;
import domain.entity.User;
import domain.jdbc.DBConnectionMgr;
import service.UserService;
import service.UserServiceImpl;
import web.dto.ReqUserDto;

@WebServlet("/modifyAccountInfo")
public class ModifyAccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService;
       
    public ModifyAccountInfo() {
    	userService = new UserServiceImpl(new ServiceDaoImpl(DBConnectionMgr.getInstance()));
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/modifyAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReqUserDto modifyReqUserDto = ReqUserDto.builder()
				.name(request.getParameter("name"))
				.password(request.getParameter("password"))
				.email(request.getParameter("email"))
				.build();
		
		System.out.println(modifyReqUserDto);
		
		int userCode = ((User) request.getSession().getAttribute("user")).getUser_code();
		
		try {
			response.setContentType("text/plain;charset=UTF-8");
			if(userService.updateUser(modifyReqUserDto, userCode)) {
				User user = (User) request.getSession().getAttribute("user");
				
				System.out.println("세션 정보 수정전: " + user.getName());
				user.setName(modifyReqUserDto.getName());
				user.setPassword(modifyReqUserDto.getPassword());
				user.setEmail(modifyReqUserDto.getEmail());
				System.out.println("세션 정보 수정후: " + ((User) request.getSession().getAttribute("user")).getName());
				
				response.getWriter().print(true);
			}else {
				response.getWriter().print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
