package service;

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
	public CarAllInfo getCarInfo(String carNumber) throws Exception {
		return serviceDao.getCarInfoByCarNumber(carNumber);
	}

}