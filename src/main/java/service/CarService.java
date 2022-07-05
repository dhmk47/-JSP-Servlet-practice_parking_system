package service;

import domain.entity.Car;

public interface CarService {
	public boolean registerCar(String car_number) throws Exception;
	public Car getCarInfo() throws Exception;
}