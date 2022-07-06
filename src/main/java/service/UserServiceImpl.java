package service;

import domain.dao.ServiceDaoImpl;
import domain.entity.User;
import lombok.RequiredArgsConstructor;
import web.dto.SignupReqUserDto;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final ServiceDaoImpl serviceDao;

	@Override
	public boolean createUser(SignupReqUserDto signupReqUserDto) throws Exception {
		return serviceDao.insertUser(signupReqUserDto.toEntity()) > 0;
	}

	@Override
	public User getUser(String username) throws Exception {
		return serviceDao.getUser(username);
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