package com.hanaden.demo.jpaspringmvc.persistence;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hanasaki
 */
public interface CarDao {

    @Transactional(value = "beanTransactionManagerBasicTest", propagation = Propagation.REQUIRES_NEW)
    public List<CarVo> getCars();

    @Transactional(value = "beanTransactionManagerBasicTest", propagation = Propagation.REQUIRES_NEW)
    public CarVo getCar(Long carId);

    @Transactional(value = "beanTransactionManagerBasicTest", propagation = Propagation.REQUIRES_NEW)
    public CarVo save(CarVo car);
}
