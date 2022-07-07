package web.servlet.api.auth;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.entity.CarAllInfo;
import domain.entity.User;
import service.CarService;
import service.CarServiceImpl;

@WebServlet("/api/v1/load/carinfo")
public class LoadCarInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CarService carService;
	
	public LoadCarInfoServlet() {
		carService = new CarServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userCode = ((User) request.getSession().getAttribute("user")).getUser_code();
		
		try {
			ArrayList<CarAllInfo> carList = carService.getCarInfoByCarCode(userCode);
			
			String carListJson = new Gson().toJson(carList);
			
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(carListJson);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
