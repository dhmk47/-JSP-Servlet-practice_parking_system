package service;

import domain.dao.ServiceDao;
import domain.dao.ServiceDaoImpl;
import domain.entity.Car;
import domain.jdbc.DBConnectionMgr;

public class CarServiceImpl implements CarService{
	private final ServiceDao serviceDao;
	
	public CarServiceImpl() {
		serviceDao = new ServiceDaoImpl(DBConnectionMgr.getInstance());
	}

	@Override
	public boolean registerCar(String car_number) throws Exception {
		return serviceDao.insertCar(car_number);
	}

	@Override
	public Car getCarInfo() throws Exception {
		return null;
	}

}