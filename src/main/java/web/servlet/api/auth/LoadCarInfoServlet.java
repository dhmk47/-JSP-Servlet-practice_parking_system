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
			
			System.out.println("carList.size(): " + carList.size());
			
			LocalDateTime now = LocalDateTime.now();
			
			int nowYear = now.getYear();
			int nowDayOfYear = now.getDayOfYear();
			int nowHour = now.getHour();
			
			int startYear = 0;
			int startDayOfYear = 0;
			int startHour = 0;
			
			int endYear = 0;
			int endDayOfYear = 0;
			int endHour = 0;
			
			int remainingDay = 0;
			int remainingHour = 0;
			
			for(int i = 0; i < carList.size(); i++) {
				CarAllInfo carInfo = carList.get(i);
				startYear = carInfo.getStart_year();
				startDayOfYear = carInfo.getStart_dayOfYear();
				startHour = carInfo.getStart_hour();
				
				endYear = carInfo.getEnd_year();
				endDayOfYear = carInfo.getEnd_dayOfYear();
				endHour = carInfo.getEnd_hour();
				
				
				remainingDay = endDayOfYear - nowDayOfYear;	// 남은 날짜 계산
				carInfo.setRemaining_day(remainingDay);		// 남은 날짜 대입
				System.out.println("endDay: " + endDayOfYear);
				System.out.println("nowDay: " + nowDayOfYear);
				System.out.println("remainingDay: " + remainingDay);
				
//				생성(15) < 현재(19)
//				현재(19) - 생성(15) = 경과시간(4)
//				24 - 경과시간(4) = 남은시간(20)
				if(startHour < nowHour + 1) { // 15시 15시로 시간이 동일할 경우 계산 방법이 같기에 +1 해줌
					remainingHour = 24 - (nowHour - startHour);
				}else {
					remainingHour = startHour - nowHour;
				}
//				생성(15) > 현재(1)
//				24 + 현재(1) - 생성(15) = 경과시간(10)
//				생성(15) - 현재(1) = 남은시간(14)
				
				
				// 초과했거나 당일일 경우 남은 시간 정의하기
				if(remainingDay < 1) {
					if(nowHour + 1 > endHour) {			// 현재시간이 큰 경우는 현재시간 - 만료시간 = 초과 시간
						remainingHour = nowHour - endHour;
					}else {								// 현재시간이 작을 경우의 공식
						remainingHour = (24 + nowHour - endHour) + (remainingDay * -1 * 24);
					}
				}
				// 초과 했을 경우
				// 24 + 현재(1) - 생성(15) = 경과시간(10)

				carInfo.setRemaining_day(remainingDay < 0 ? remainingDay : remainingDay - 1);
				carInfo.setRemaining_hour(remainingHour);
				
				session.setAttribute("car" + i, carInfo);
			}
			session.setAttribute("size", carList.size());
			
//			session.setAttribute("carList", carList);
			
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
