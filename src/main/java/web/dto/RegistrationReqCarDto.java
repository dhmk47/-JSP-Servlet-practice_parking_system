package web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegistrationReqCarDto {
	private String car_number;
	private int dayPass;
}
