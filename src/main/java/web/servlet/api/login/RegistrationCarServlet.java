package web.servlet.api.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CarService;
import service.CarServiceImpl;

@WebServlet("/registrationCar")
public class RegistrationCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CarService carService;
	
	public RegistrationCarServlet() {
		carService = new CarServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String car_number = request.getParameter("car_number");
			carService.registerCar(car_number);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}