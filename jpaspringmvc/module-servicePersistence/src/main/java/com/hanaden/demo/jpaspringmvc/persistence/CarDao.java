package com.hanaden.demo.jpaspringmvc.persistence;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import java.util.List;

/**
 *
 * @author hanasaki
 */
public interface CarDao {

    public List<CarVo> getCars();

    public void delete(final long id);

    public CarVo getCar(Long carId);

    public CarVo save(CarVo car);
}
