package com.hanaden.demo.jpaspringmvc.persistence.hibernate;

/**
 *
 * @author hanasaki
 */
import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.domain.hibernate.CarVoHibernate;
import com.hanaden.demo.jpaspringmvc.persistence.CarDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CarDaoHibernateImpl implements CarDao {

    protected EntityManager entityManager;
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CarVo> getCars() {
        Query query = getEntityManager().createQuery("select c from CarVoHibernate c");
        List<CarVo> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public CarVoHibernate getCar(Long carId) {
        return getEntityManager().find(CarVoHibernate.class, carId);
    }

    @Override
    public CarVo save(CarVo car) {
        entityManager.persist(car);
        return (car);
    }
}