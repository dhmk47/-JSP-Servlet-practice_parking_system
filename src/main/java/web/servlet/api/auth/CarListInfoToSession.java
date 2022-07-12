package web.servlet.api.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import domain.entity.CarAllInfo;

@WebServlet("/api/v1/carListInfo/session")
public class CarListInfoToSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<CarAllInfo> carList = new ArrayList<CarAllInfo>();
//		Object carObj = session.getAttribute("carList");
//		List<CarAllInfo> carList = Arrays.asList((CarAllInfo) carObj);
		
		int size = (Integer) session.getAttribute("size");
		
		for(int i = 0; i < size; i++) {
			carList.add((CarAllInfo) session.getAttribute("car" + i));
		}
		
		System.out.println(carList);
		response.setContentType("application/json;charset=UTF-8");
		String carJsonList = new Gson().toJson(carList);
		response.getWriter().print(carJsonList);
	}

}
