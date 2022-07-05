package domain.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private int user_code;
	private String name;
	private String email;
	private String username;
	private String password;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
}