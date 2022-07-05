package domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingTicket {
	private int ticket_code;
	private String parKing_ticket;
}