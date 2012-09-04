package com.hanaden.demo.jpaspringmvc.http.controller;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanasaki
 */
@Controller
public class TestController {

    @Autowired
    private TestBusinessService testBusinessService;

    private CarVo buildCarFromRequest(final HttpServletRequest request) {
        CarVo retVal = null;

        return (retVal);
    }

    @RequestMapping(value = "/parkinglot")
    public ModelAndView parkCar(final HttpServletRequest request) {
        ModelAndView retVal;

        final String name = request.getParameter("name");

        testBusinessService.parkCar(name);

        Map model = null;

        retVal = new ModelAndView("guest.jsp", "model", model);
        
        return (retVal);
    }
    
}
