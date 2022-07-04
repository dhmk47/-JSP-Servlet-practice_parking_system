package web.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegistrationReqCarDto {
	private String car_number;
	private int dayPass;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
}
