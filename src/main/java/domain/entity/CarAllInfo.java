package domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarAllInfo {
	private int car_code;
	private String car_number;
	private String ticket_dtl;
	private int start_year;
	private int start_dayOfYear;
	private int start_hour;
}