package com.hanaden.demo.jpaspringmvc.service.business;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import java.util.List;

/**
 *
 * @author hanasaki
 */
public interface TestBusinessService {

    CarVo findCarById(final Long id);

    List<CarVo> getParkedCarsList();

    CarVo parkCar(CarVo car);

    void unparkCar(final Long id);
    
}
