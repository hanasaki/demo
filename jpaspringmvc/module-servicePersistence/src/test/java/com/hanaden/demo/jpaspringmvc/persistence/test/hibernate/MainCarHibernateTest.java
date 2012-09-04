package com.hanaden.demo.jpaspringmvc.persistence.test.hibernate;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.persistence.CarDao;
import com.hanaden.demo.jpaspringmvc.domain.hibernate.CarVoHibernate;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hanasaki
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "/conf/spring/test/spring-BasicTestHibernate.xml"
})
@Transactional(value = "beanTransactionManagerBasicTest")
@TransactionConfiguration(defaultRollback = true,
transactionManager = "beanTransactionManagerBasicTest")
public class MainCarHibernateTest {

    @Resource(name = "beanCarDao")
    protected CarDao carDao;
    private static final Logger logger = Logger.getLogger(MainCarHibernateTest.class);
    protected long pk;

    @Before
    @Transactional(value = "beanTransactionManagerBasicTest", propagation = Propagation.REQUIRES_NEW)
    public void init() {
        Assert.assertNotNull(carDao);
        //        
        pk = 0;

        //
        CarVo car = new CarVoHibernate();
        car.setCompany("company");
        car.setModel("model");
        car.setPrice(234);

        //
//        Assert.assertNull("PK should be null : car.pk = " + car.getId(), car.getId());
        car = carDao.save(car);
        pk = car.getId();
        Assert.assertNotNull("PK should be !null : pk = " + pk, pk); // same as car

        //
        List<CarVo> cars = carDao.getCars();

        //
        logger.debug((ReflectionToStringBuilder.toString(car)));
    }

    @Test
    public void listCarsTest() {
        List<CarVo> cars = carDao.getCars();
        //
        Assert.assertNotNull(cars);
        Assert.assertEquals(1, cars.size());
    }

    @Test
    public void getCarTest() {
        CarVo car = carDao.getCar(pk);
        //
        Assert.assertEquals(pk, car.getId());
        // Assert.assertEquals("Boxster", car.getModel());
    }
}
