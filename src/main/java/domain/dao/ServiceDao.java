package domain.dao;

import domain.entity.User;

public interface ServiceDao {
	public int insertUser(User user) throws Exception;
	public User getUser(String username) throws Exception;
	public boolean modifyUser() throws Exception;
	public boolean removeUser() throws Exception;
	public boolean insertCar(String car_number) throws Exception;
}