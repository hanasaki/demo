package com.hanaden.demo.jpaspringmvc.webservice.impl;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.domain.CarVoBase;
import com.hanaden.demo.jpaspringmvc.http.controller.TestController;
import com.hanaden.demo.jpaspringmvc.service.business.TestBusinessService;
import com.hanaden.demo.jpaspringmvc.webservice.ParkingLotWebserviceREST;
import java.util.List;
import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * @author hanasaki
 */
//@WebService(serviceName = "ParkingLotWebserviceREST",
//endpointInterface = "com.hanaden.demo.jpaspringmvc.webservice.ParkingLotWebserviceREST")
@Path("/parkingLot/")
public class ParkingLotWebserviceRESTImpl implements ParkingLotWebserviceREST {

    private Logger logger = Logger.getLogger(TestController.class);
    private final String KEY = "carVo";
    @Resource(name = "beanTestBusinessService")
    private TestBusinessService testBusinessService;

    @GET
    @Path("/findCarById/{id}")
//    @Produces("text/plain")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public CarVo findCarById(@PathParam("id") Long id) {
        CarVo retVal;

        retVal = testBusinessService.findCarById(id);
        retVal = new CarVoBase();
        retVal.setId(id);
        retVal.setManufacturer("man");

        return (retVal);
    }

    @GET
    @Path("/l")
    @Produces(MediaType.TEXT_PLAIN)
    public String l() {
        return ("ddd");
    }

    @GET
    @Path("/listCarsInLot")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<CarVo> listCarsInLot() {
        return (testBusinessService.listCarsInLot());
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
