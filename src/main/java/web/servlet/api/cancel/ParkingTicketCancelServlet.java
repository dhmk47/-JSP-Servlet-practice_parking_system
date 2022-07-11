package web.servlet.api.cancel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CarService;
import service.CarServiceImpl;

@WebServlet("/api/v1/ticket/cancel")
public class ParkingTicketCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CarService carService;
       
    public ParkingTicketCancelServlet() {
    	carService = new CarServiceImpl();
    }

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int carCode = Integer.parseInt(request.getParameter("car_code"));
		response.setContentType("text/plain;charset=UTF-8");
		try {
			if(carService.parkingTicketCancle(carCode)) {
				response.getWriter().print(true);
			}else {
				response.getWriter().print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
