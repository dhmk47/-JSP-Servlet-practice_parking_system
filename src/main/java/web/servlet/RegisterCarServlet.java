package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.entity.CarAllInfo;
import service.CarService;
import service.CarServiceImpl;

@WebServlet("/registerCar")
public class RegisterCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CarService carService;
       
    public RegisterCarServlet() {
    	carService = new CarServiceImpl();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carNumber = request.getParameter("carNumber");
		int ticketCode = Integer.parseInt(request.getParameter("parkingTicket"));
		
		try {
			if(carService.registerCar(carNumber, ticketCode)) {
				CarAllInfo carInfo = carService.getCarInfo(carNumber);
				
				response.getWriter().print(true);
			}else {
				response.getWriter().print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}