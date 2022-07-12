package web.servlet.api.delete;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CarService;
import service.CarServiceImpl;

@WebServlet("/api/v1/car/delete")
public class DeleteCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CarService carService;
       
    public DeleteCarServlet() {
    	carService = new CarServiceImpl();
    }

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int carCode = Integer.parseInt(request.getParameter("carCode"));
		
		response.setContentType("text/plain;charset=UTF-8");
		
		try {
			if(carService.removeCar(carCode)) {
				response.getWriter().print(true);
			}else {
				response.getWriter().print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}