package web.servlet.api.auth;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		int userCode = ((User) session.getAttribute("user")).getUser_code();
		
		try {
			ArrayList<CarAllInfo> carList = carService.getCarInfoByCarCode(userCode);
			
			for(int i = 0; i < carList.size(); i++) {
				session.setAttribute("car" + i, carList.get(i));
			}
			session.setAttribute("size", carList.size());
			
			// localdatetime now 로 현재 시간을 받은 다음 반복문으로 모두 set해줘서
			// 그 값을 바탕으로 클라이언트에 뿌리는 방법?
			
			String carListJson = new Gson().toJson(carList);
			
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(carListJson);
				

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
