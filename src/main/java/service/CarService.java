package service;

import domain.entity.Car;

public interface CarService {
	public boolean registerCar(String carNumber, int ticketCode) throws Exception;
	public Car getCarInfo(String carNumer) throws Exception;
}