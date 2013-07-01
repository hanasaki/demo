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
import org.apache.log4j.Logger;

public class CarDaoJpaImpl implements CarDao {
    
    protected EntityManager entityManager;
    private static final Logger logger = Logger.getLogger(CarDaoJpaImpl.class);
    
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
    
    @Override
    public void delete(final long id) {
        Query query = getEntityManager().createQuery("delete c from CarVoHibernate c WHERE c.id == :ID");
        query.setParameter("ID", id);
        int queryCount = query.executeUpdate();
        logger.debug("deleleted " + queryCount + " records for ID=" + id);
    }
}
