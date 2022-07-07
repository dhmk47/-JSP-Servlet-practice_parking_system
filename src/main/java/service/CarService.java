package service;

import domain.entity.CarAllInfo;

public interface CarService {
	public boolean registerCar(String carNumber, int ticketCode) throws Exception;
	public CarAllInfo getCarInfo(String carNumer) throws Exception;
}