package service;

import web.dto.SignupReqUserDto;

public class UserServiceImpl implements UserService{

	@Override
	public boolean createUser(SignupReqUserDto signupReqUserDto) throws Exception {
		System.out.println(signupReqUserDto);
		return false;
	}

	@Override
	public SignupReqUserDto getUser() throws Exception {
		return null;
	}

	@Override
	public boolean updateUser() throws Exception {
		return false;
	}

	@Override
	public boolean deleteUser() throws Exception {
		return false;
	}

}