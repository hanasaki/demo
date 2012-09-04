package com.hanaden.demo.jpaspringmvc.persistence.hibernate;

/**
 *
 * @author hanasaki
 */
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//@Repository
public class GenericDaoJpaImpl<T_DOMAIN_CLASS, T_PK extends Serializable>
        implements GenericDaoInterface<T_DOMAIN_CLASS, T_PK> {
    
    private final Class<T_DOMAIN_CLASS> persistentClass;
    protected EntityManager entityManager;
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    @SuppressWarnings("unchecked")
    public GenericDaoJpaImpl() {
//        this.persistentClass = null;
        this.persistentClass = (Class<T_DOMAIN_CLASS>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     *
     * @param persistentClass
     */
    public GenericDaoJpaImpl(final Class<T_DOMAIN_CLASS> persistentClass) {
        this.persistentClass = persistentClass;
    }
    
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public Class<T_DOMAIN_CLASS> getEntityClass() {
        return (persistentClass);
    }
    
    @Override
    public T_DOMAIN_CLASS findByPk(T_PK id) {
        return (entityManager.find(persistentClass, id));
    }
    
    @Override
    public T_DOMAIN_CLASS findByPk(T_PK id, LOCK_MODE lockOptions) {
        T_DOMAIN_CLASS retVal = null;
        
        LockModeType lmt;
        lmt = lockMode2LockOptions(lockOptions);
        retVal = entityManager.find(persistentClass, retVal, lmt);
        
        return (retVal);
    }
    
    @Override
    public List<T_DOMAIN_CLASS> findAll() {
        String qString = "";
        Query q = entityManager.createQuery(qString);
        return (q.getResultList());
    }
    
    @Override
    public List<T_DOMAIN_CLASS> findByExample(T_DOMAIN_CLASS exampleInstance) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<T_DOMAIN_CLASS> findByNamedQuery(String queryName, Object... params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<T_DOMAIN_CLASS> findByNamedQueryAndNamedParams(String queryName, Map<String, ? extends Object> params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int countAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int countByExample(T_DOMAIN_CLASS exampleInstance) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public T_DOMAIN_CLASS saveOrUpdate(T_DOMAIN_CLASS entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public T_DOMAIN_CLASS merge(T_DOMAIN_CLASS entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void delete(T_DOMAIN_CLASS entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int deleteAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    static private LockModeType lockMode2LockOptions(LOCK_MODE lockMode) {
        LockModeType retVal = LockModeType.NONE;
        switch (lockMode) {
            case NONE: {
                retVal = LockModeType.NONE;
                break;
            }
            case READ: {
                retVal = LockModeType.PESSIMISTIC_READ;
                break;
            }
            case WRITE: {
                retVal = LockModeType.PESSIMISTIC_WRITE;
                break;
            }
        }
        return (retVal);
    }
}
