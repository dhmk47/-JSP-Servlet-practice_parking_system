package web.servlet.api.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.entity.User;

@WebServlet("/api/v1/userinfo/session")
public class UserInfoToSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String userJsonData = gson.toJson((User) request.getSession().getAttribute("user"));
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(userJsonData);
	}

}