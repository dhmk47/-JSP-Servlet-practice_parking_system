package service;

import domain.dao.ServiceDaoImpl;
import domain.entity.User;
import lombok.RequiredArgsConstructor;
import web.dto.ReqUserDto;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final ServiceDaoImpl serviceDao;

	@Override
	public boolean createUser(ReqUserDto signupReqUserDto) throws Exception {
		return serviceDao.insertUser(signupReqUserDto.toEntity()) > 0;
	}

	@Override
	public User getUser(String username) throws Exception {
		return serviceDao.getUser(username);
	}

	@Override
	public boolean updateUser(ReqUserDto modifyReqUserDto, int userCode) throws Exception {
		System.out.println(modifyReqUserDto.toEntity());
		return serviceDao.modifyUser(modifyReqUserDto.toEntity(), userCode);
	}

	@Override
	public boolean createUserDtl(int userCode, int carCode) throws Exception {
		return serviceDao.insertUserDtl(userCode, carCode);
	}
	
	@Override
	public boolean deleteUser() throws Exception {
		return false;
	}


}