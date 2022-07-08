package service;

import domain.entity.User;
import web.dto.ReqUserDto;

public interface UserService {
	public boolean createUser(ReqUserDto signupReqUserDto) throws Exception;
	public User getUser(String username) throws Exception;
	public boolean updateUser(ReqUserDto modifyReqUserDto, int userCode) throws Exception;
	public boolean createUserDtl(int userCode, int carCode) throws Exception;
	public boolean deleteUser() throws Exception;
}