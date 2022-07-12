package service;

import java.util.ArrayList;

import domain.entity.CarAllInfo;
import web.dto.RegistrationReqCarDto;

public interface CarService {
	public boolean registerCar(RegistrationReqCarDto reqCarDto) throws Exception;
	public CarAllInfo getCarInfoByCarNumber(String carNumer) throws Exception;
	public ArrayList<CarAllInfo> getCarInfoByCarCode(int carCode) throws Exception;
	public boolean parkingTicketCancle(int carCode) throws Exception;
	public boolean updateParkingTicket(RegistrationReqCarDto reqCarDto, int carCode) throws Exception;
	public boolean removeCar(int carCode) throws Exception;
}