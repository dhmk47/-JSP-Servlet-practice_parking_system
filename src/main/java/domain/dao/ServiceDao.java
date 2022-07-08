package domain.dao;

import java.util.ArrayList;

import domain.entity.CarAllInfo;
import domain.entity.User;

public interface ServiceDao {
	public int insertUser(User user) throws Exception;
	public User getUser(String username) throws Exception;
	public boolean modifyUser(User user, int user_code) throws Exception;
	public boolean insertUserDtl(int user_code, int car_code) throws Exception;
	public boolean removeUser() throws Exception;
	public int insertCar(String car_number, int ticket_code, int year, int dayOfYear, int hour) throws Exception;
	public CarAllInfo getCarInfoByCarNumber(String car_number) throws Exception;
	public ArrayList<CarAllInfo> getCarInfoByCarCode(int car_code) throws Exception;
}