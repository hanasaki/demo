package com.hanaden.demo.jpaspringmvc.http.controller;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.persistence.CarDao;
import java.util.List;

/**
 *
 * @author hanasaki
 */
public class TestBusinessService {

    private CarDao carDao;

    public void parkCar(String name) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public List<CarVo> listCarsInLot() {
        List<CarVo> retVal = carDao.getCars();
        return (retVal);
    }

    public void unparkCar(final Long id) {
    }
}
