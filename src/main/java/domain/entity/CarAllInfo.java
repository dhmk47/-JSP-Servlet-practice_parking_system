package domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarAllInfo {
	private int car_code;
	private String car_number;
	private int ticket_code;
	private String ticket_dtl;
	private int start_year;
	private int start_dayOfYear;
	private int start_hour;
	private int end_year;
	private int end_dayOfYear;
	private int end_hour;
	private int remaining_day;
	private int remaining_hour;
}