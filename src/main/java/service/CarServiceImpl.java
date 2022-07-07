package service;

import java.util.ArrayList;

import domain.dao.ServiceDao;
import domain.dao.ServiceDaoImpl;
import domain.entity.CarAllInfo;
import domain.jdbc.DBConnectionMgr;

public class CarServiceImpl implements CarService{
	private final ServiceDao serviceDao;
	
	public CarServiceImpl() {
		serviceDao = new ServiceDaoImpl(DBConnectionMgr.getInstance());
	}

	@Override
	public boolean registerCar(String carNumber, int ticketCode) throws Exception {
		return serviceDao.insertCar(carNumber, ticketCode) == 2 ? true : false;
	}

	@Override
	public CarAllInfo getCarInfoByCarNumber(String carNumber) throws Exception {
		return serviceDao.getCarInfoByCarNumber(carNumber);
	}

	@Override
	public ArrayList<CarAllInfo> getCarInfoByCarCode(int carCode) throws Exception {
		return serviceDao.getCarInfoByCarCode(carCode);
	}

}