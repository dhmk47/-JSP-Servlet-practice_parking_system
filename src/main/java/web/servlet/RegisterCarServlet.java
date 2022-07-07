package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.dao.ServiceDaoImpl;
import domain.entity.CarAllInfo;
import domain.entity.User;
import domain.jdbc.DBConnectionMgr;
import service.CarService;
import service.CarServiceImpl;
import service.UserService;
import service.UserServiceImpl;

@WebServlet("/registerCar")
public class RegisterCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CarService carService;
	private final UserService userService;
       
    public RegisterCarServlet() {
    	carService = new CarServiceImpl();
    	userService = new UserServiceImpl(new ServiceDaoImpl(DBConnectionMgr.getInstance()));
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carNumber = request.getParameter("carNumber");
		int ticketCode = Integer.parseInt(request.getParameter("parkingTicket"));
		
		try {
			if(carService.registerCar(carNumber, ticketCode)) {
				CarAllInfo carInfo = carService.getCarInfoByCarNumber(carNumber);
				int user_code = ((User) request.getSession().getAttribute("user")).getUser_code();
				System.out.println(user_code);
				if(userService.createUserDtl(user_code, carInfo.getCar_code())) {
					response.getWriter().print(true);
				}
			}else {
				response.getWriter().print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}