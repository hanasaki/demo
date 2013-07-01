package com.hanaden.demo.jpaspringmvc.http.controller;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.domain.CarVoBase;
import com.hanaden.demo.jpaspringmvc.service.business.TestBusinessService;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author hanasaki
 *
 * http://static.springsource.org/spring/docs/3.0.x/reference/mvc.html
 *
 */
@Controller
@RequestMapping(value = "/parking")
public class TestController {

    private Logger logger = Logger.getLogger(TestController.class);
    private final String KEY = "carVo";
    @Resource(name = "beanTestBusinessService")
    private TestBusinessService testBusinessService;

    public TestController() {
//        logger.warn("ctor");
    }

    private CarVo setupCar(ModelMap model) {
        CarVo retVal = new CarVoBase();
        model.put(KEY, retVal);
        return (retVal);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CarVo> get(ModelMap model) {
        List<CarVo> retVal;
        setupCar(model);

        retVal = testBusinessService.getParkedCarsList();

        return (retVal);
    }

    @RequestMapping(value = "getCarById/{id}", method = RequestMethod.GET,
    consumes = {"application/json", "application/xml"},
    produces = {"application/json", "application/xml"})
    public @ResponseBody
    CarVo getCarById(@PathVariable Long id, ModelMap model) {
        CarVo retVal = setupCar(model);

        retVal = testBusinessService.findCarById(id);

        return (retVal);
    }

    @RequestMapping(value = "parkCar", method = RequestMethod.POST,
    consumes = {"application/json", "application/xml"},
    produces = {"application/json", "application/xml"})
    public @ResponseBody
    CarVo parkCar(@RequestBody CarVoBase car, ModelMap map) {
        CarVo retVal = setupCar(map);

        retVal = testBusinessService.parkCar(car);

        return (car);
    }

    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException ex, HttpServletRequest request) {
        logger.error(ex.getLocalizedMessage());
        return ClassUtils.getShortName(ex.getClass());
    }
}
