package com.hanaden.demo.jpaspringmvc.webservice.impl;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.http.controller.TestController;
import com.hanaden.demo.jpaspringmvc.service.business.TestBusinessService;
import com.hanaden.demo.jpaspringmvc.webservice.ParkingLotWebserviceSOAP;
import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebService;
import org.apache.log4j.Logger;

/**
 *
 * @author hanasaki
 */
@WebService(serviceName = "ParkingLotWebserviceSOAP",
endpointInterface = "com.hanaden.demo.jpaspringmvc.webservice.ParkingLotWebserviceSOAP")
public class ParkingLotWebserviceSOAPImpl implements ParkingLotWebserviceSOAP {

    private Logger logger = Logger.getLogger(TestController.class);
    private final String KEY = "carVo";
    @Resource(name = "beanTestBusinessService")
    private TestBusinessService testBusinessService;

    @Override
    public CarVo findCarById(Long id) {
        return (testBusinessService.findCarById(id));
    }

    @Override
    public List<CarVo> getParkedCarsList() {
        return (testBusinessService.getParkedCarsList());
    }

    @Override
    public CarVo parkCar(CarVo car) {
        return (parkCar(car));
    }

    @Override
    public void unparkCar(Long id) {
        unparkCar(id);
    }

}
