package service;

import web.dto.SignupReqUserDto;

public interface UserService {
	public boolean createUser(SignupReqUserDto signupReqUserDto) throws Exception;
	public SignupReqUserDto getUser() throws Exception;
	public boolean updateUser() throws Exception;
	public boolean deleteUser() throws Exception;
}