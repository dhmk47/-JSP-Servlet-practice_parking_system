package web.dto;

import domain.entity.CarAllInfo;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegistrationReqCarDto {
	private String car_number;
	private int ticket_code;
	private int start_year;
	private int start_dayOfYear;
	private int start_hour;
	private int end_year;
	private int end_dayOfYear;
	private int end_hour;
	
	public CarAllInfo toEntity() {
		return CarAllInfo.builder()
				.car_number(car_number)
				.ticket_code(ticket_code)
				.start_year(start_year)
				.start_dayOfYear(start_dayOfYear)
				.start_hour(start_hour)
				.end_year(end_year)
				.end_dayOfYear(end_dayOfYear)
				.end_hour(end_hour)
				.build();
	}
	
}