package service;

import domain.entity.User;
import web.dto.SignupReqUserDto;

public interface UserService {
	public boolean createUser(SignupReqUserDto signupReqUserDto) throws Exception;
	public User getUser(String username) throws Exception;
	public boolean updateUserMst() throws Exception;
	public boolean createUserDtl(int userCode, int carCode) throws Exception;
	public boolean deleteUser() throws Exception;
}