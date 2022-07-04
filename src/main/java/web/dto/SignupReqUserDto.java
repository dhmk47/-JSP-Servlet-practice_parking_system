package web.dto;

import domain.entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignupReqUserDto {
	private String name;
	private String email;
	private String username;
	private String password;
	
	public User toEntity() {
		return User.builder()
				.name(name)
				.email(email)
				.username(username)
				.password(password)
				.build();
	}
}