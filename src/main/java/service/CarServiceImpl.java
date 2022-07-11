package service;

import java.util.ArrayList;

import domain.dao.ServiceDao;
import domain.dao.ServiceDaoImpl;
import domain.entity.CarAllInfo;
import domain.jdbc.DBConnectionMgr;
import web.dto.RegistrationReqCarDto;

public class CarServiceImpl implements CarService{
	private final ServiceDao serviceDao;
	
	public CarServiceImpl() {
		serviceDao = new ServiceDaoImpl(DBConnectionMgr.getInstance());
	}

	@Override
	public boolean registerCar(RegistrationReqCarDto reqCarDto) throws Exception {
		return serviceDao.insertCar(reqCarDto.toEntity()) == 2 ? true : false;
	}

	@Override
	public CarAllInfo getCarInfoByCarNumber(String carNumber) throws Exception {
		return serviceDao.getCarInfoByCarNumber(carNumber);
	}

	@Override
	public ArrayList<CarAllInfo> getCarInfoByCarCode(int carCode) throws Exception {
		return serviceDao.getCarInfoByCarCode(carCode);
	}

	@Override
	public boolean parkingTicketCancle(int car_code) throws Exception {
		return serviceDao.parkingTicketCancelUpdate(car_code) != 0;
	}

	@Override
	public boolean updateParkingTicket(RegistrationReqCarDto reqCarDto, int carCode) throws Exception {
		return serviceDao.updateParkingTicket(reqCarDto.toEntity(), carCode) != 0;
	}

}