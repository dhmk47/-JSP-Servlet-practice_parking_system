package service;

import java.util.ArrayList;

import domain.entity.CarAllInfo;

public interface CarService {
	public boolean registerCar(String carNumber, int ticketCode, int year, int dayOfYear, int hour) throws Exception;
	public CarAllInfo getCarInfoByCarNumber(String carNumer) throws Exception;
	public ArrayList<CarAllInfo> getCarInfoByCarCode(int carCode) throws Exception;
}