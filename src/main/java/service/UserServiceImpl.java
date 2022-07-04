package service;

import domain.dao.ServiceDao;
import lombok.RequiredArgsConstructor;
import web.dto.SignupReqUserDto;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final ServiceDao serviceDao;

	@Override
	public boolean createUser(SignupReqUserDto signupReqUserDto) throws Exception {
		System.out.println(signupReqUserDto);
		
		return serviceDao.insertUser(signupReqUserDto.toEntity()) > 0;
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