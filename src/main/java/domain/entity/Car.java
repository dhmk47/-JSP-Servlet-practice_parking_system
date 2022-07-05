package domain.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Car {
	private int car_code;
	private String car_number;
}