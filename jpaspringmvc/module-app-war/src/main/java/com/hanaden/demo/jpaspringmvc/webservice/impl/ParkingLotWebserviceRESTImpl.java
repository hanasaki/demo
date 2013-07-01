package com.hanaden.demo.jpaspringmvc.webservice.impl;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.http.controller.TestController;
import com.hanaden.demo.jpaspringmvc.service.business.TestBusinessService;
import com.hanaden.demo.jpaspringmvc.webservice.ParkingLotWebserviceREST;
import java.util.List;
import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

// http://localhost:8088/app-war/apache-cxf-servlet/restServicePort/restServicePort/parkingLot/findCarById/4
// http://localhost:8088/app-war/apache-cxf-servlet/restServicePort/parkingLot/getParkedCarsList
/**
 *
 * @author hanasaki
 */
@Path("/parkingLot/")
public class ParkingLotWebserviceRESTImpl implements ParkingLotWebserviceREST {

    private Logger logger = Logger.getLogger(TestController.class);
    private final String KEY = "carVo";
    @Resource(name = "beanTestBusinessService")
    private TestBusinessService testBusinessService;

    @javax.ws.rs.GET
    @javax.ws.rs.Path("{filename}")
    @javax.ws.rs.Produces("*/*")
    javax.ws.rs.core.Response getDirectoryOrFileContents(
            @javax.ws.rs.PathParam("filename") String filename,
            @javax.ws.rs.core.Context javax.ws.rs.core.HttpHeaders headers) {
        return null;
    }

    @GET
    @Path("/findCarById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CarVo findCarById(@PathParam("id") Long id,
            @Context HttpHeaders headers) {
        CarVo retVal;

        retVal = findCarById(id);

        return (retVal);
    }

    @Override
    public CarVo findCarById(Long id) {
        CarVo retVal;

        retVal = testBusinessService.findCarById(id);

        return (retVal);
    }

    @GET
    @Path("/getParkedCarsList")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<CarVo> getParkedCarsList() {
        return (testBusinessService.getParkedCarsList());
    }

    @PUT
    @Path("/parkCar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public CarVo parkCar(CarVo car) {
        return (testBusinessService.parkCar(car));
    }

    @DELETE
    @Path("/unparkCar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public void unparkCar(Long id) {
        testBusinessService.unparkCar(id);
    }
}
