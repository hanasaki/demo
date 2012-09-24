package com.hanaden.demo.jpaspringmvc.persistence.test.mybatis;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.domain.CarVoBase;
import com.hanaden.demo.jpaspringmvc.domain.hibernate.CarVoHibernate;
import com.hanaden.demo.jpaspringmvc.persistence.CarDao;
import com.hanaden.demo.jpaspringmvc.persistence.mybatis.GenerickDaoMyBatisInterface;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
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
    "/conf/spring/test/spring-BasicTestMyBatis.xml"
})
@Transactional(value = "beanTransactionManagerBasicTest")
@TransactionConfiguration(defaultRollback = true,
transactionManager = "beanTransactionManagerBasicTest")
public class MainCarMyBatisTest {
//http://blog.idleworx.com/2011/09/mybatis-dao-example-code-tutorial.html

    @Resource(name = "beanCarDao")
    protected CarDao carDao;
    private static final Logger logger = Logger.getLogger(MainCarMyBatisTest.class);
    protected long pk;
    @Resource(name = "beanDatasourceBasicTest")
    protected DataSource ds;
    
    public void createTable() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS `CAR`"
                + "( "
                + "ID int identity primary key not null,"
                + "COMPANY varchar(25),"
                + "MODEL varchar (25),"
                + "PRICE decimal(10,2) ); ";
        Connection connection = ds.getConnection();
        Statement statement = connection.createStatement();
        boolean retVal = statement.execute(sql);

//        if (!retVal) {
//            throw new IllegalStateException(sql + " : retVal = " + retVal);
//        }
    }
    
    @Before
    @Transactional(value = "beanTransactionManagerBasicTest", propagation = Propagation.REQUIRES_NEW)
    public void init() throws Throwable {
        Assert.assertNotNull(carDao);
        //
        createTable();
        //        
        pk = 0;

        //
        CarVo car = new CarVoHibernate();
        car.setManufacturer("company");
        car.setModel("model");
        car.setYear(234);

        //
//        Assert.assertNull("PK should be null : car.pk = " + car.getId(), car.getId());
        car = carDao.save(car);
        pk = car.getId();
        Assert.assertNotNull("PK should be !null : pk = " + pk, pk); // same as car

        //
        List<CarVo> cars = carDao.getCars();
        pk = cars.get(0).getId();

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
