package com.hanaden.demo.jpaspringmvc.persistence.mybatis;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.persistence.CarDao;
import java.util.List;

/**
 *
 * @author hanasaki
 */
public class CarDaoMyBatisAdatper implements CarDao {

    private CarDaoMyBatis dao;

    public void setDao(CarDaoMyBatis carDao) {
        this.dao = carDao;
    }

    @Override
    public List<CarVo> getCars() {
        List<CarVo> retVal;
        List l = dao.getCars_();
        retVal = (List<CarVo>) l;

        return (retVal);
    }

    @Override
    public CarVo getCar(Long carId) {
        CarVo retVal = dao.getCar_(carId);
        return (retVal);
    }

    @Override
    public CarVo save(CarVo car) {
        dao.save_(car);
        return (car);
    }
}
