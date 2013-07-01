package com.hanaden.demo.jpaspringmvc.service.business.impl;

import com.hanaden.demo.jpaspringmvc.service.business.TestBusinessService;
import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.persistence.CarDao;
import java.util.List;

/**
 *
 * @author hanasaki
 */
public class TestBusinessServiceImpl implements TestBusinessService {

    private CarDao carDao;

    @Override
    public CarVo parkCar(CarVo car) {
        CarVo retVal = carDao.save(car);
        return (retVal);
    }

    @Override
    public List<CarVo> getParkedCarsList() {
        List<CarVo> retVal = carDao.getCars();
        return (retVal);
    }

    @Override
    public void unparkCar(final Long id) {
        carDao.delete(id);
    }

    @Override
    public CarVo findCarById(final Long id) {
        CarVo retVal = carDao.getCar(id);
        return (retVal);
    }

    /**
     * @param carDao the carDao to set
     */
    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }
}
