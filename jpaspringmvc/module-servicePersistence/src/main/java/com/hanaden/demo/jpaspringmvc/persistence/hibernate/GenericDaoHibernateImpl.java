package com.hanaden.demo.jpaspringmvc.persistence.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @param <T_DOMAIN_CLASS>
 * @param <T_PK>
 * @author Fred Bloom
 *
 * // adapted from
 * http://www.bejug.org/confluenceBeJUG/display/BeJUG/Generic+DAO+example
 *
 */
public class GenericDaoHibernateImpl<T_DOMAIN_CLASS, T_PK extends Serializable>
        extends HibernateDaoSupport
        implements GenericDaoInterface<T_DOMAIN_CLASS, T_PK> {

    // --------------------------------------------------------
    private final Class<T_DOMAIN_CLASS> persistentClass;

    /**
     *
     */
    @SuppressWarnings("unchecked")
    public GenericDaoHibernateImpl() {
        this.persistentClass = (Class<T_DOMAIN_CLASS>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     *
     * @param persistentClass
     */
    public GenericDaoHibernateImpl(final Class<T_DOMAIN_CLASS> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public int countAll() {
        return countByCriteria();
    }

    /**
     *
     * @return
     */
    protected final Session getSessionThis() {
        Session retVal = null;

        retVal = getSessionFactory().getCurrentSession();
//        retVal = super.getSession(false);
//        retVal.setFlushMode(FlushMode.AUTO);
        retVal.setFlushMode(FlushMode.COMMIT);
//        logger.debug("FLUSHMODE=" + retVal.getFlushMode().toString());

        return (retVal);
    }

    /**
     *
     */
    @Override
    public int countByExample(final T_DOMAIN_CLASS exampleInstance) {
        int retVal;
        Session session = getSessionThis();

        Criteria crit = session.createCriteria(getEntityClass());
        crit.setProjection(Projections.rowCount());
        crit.add(Example.create(exampleInstance));

        retVal = (Integer) crit.list().get(0);

        return (retVal);
    }

    /**
     *
     */
    @Override
    public List<T_DOMAIN_CLASS> findAll() {
        return findByCriteria();
    }

    /**
     *
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T_DOMAIN_CLASS> findByExample(final T_DOMAIN_CLASS exampleInstance) {
        List<T_DOMAIN_CLASS> retVal = null;

        Session session = getSessionThis();
        Criteria crit = session.createCriteria(getEntityClass());
        retVal = crit.list();

        return retVal;
    }

    /**
     *
     *
     */
    @Override
    public T_DOMAIN_CLASS findByPk(final T_PK id) {
        return (findByPk(id, LOCK_MODE.NONE));
    }

    static private LockOptions lockMode2LockOptions(LOCK_MODE lockMode) {
        LockOptions retVal = LockOptions.NONE;
        switch (lockMode) {
            case NONE: {
                retVal = LockOptions.NONE;
                break;
            }
            case READ: {
                retVal = LockOptions.READ;
                break;
            }
            case WRITE: {
                retVal = LockOptions.UPGRADE;
                break;
            }
        }
        return (retVal);
    }

    /**
     *
     * @param id
     * @param lockMode
     * @return
     */
    @Override
    public T_DOMAIN_CLASS findByPk(final T_PK id, LOCK_MODE lockMode) {
        T_DOMAIN_CLASS result;

        LockOptions lockOptions = lockMode2LockOptions(lockMode);

        Object obj =
                getSessionThis().load(persistentClass, id, lockOptions);
        result = (T_DOMAIN_CLASS) obj;

        return result;
    }

    /**
     * @param name
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T_DOMAIN_CLASS> findByNamedQuery(final String name, Object... params) {
        List<T_DOMAIN_CLASS> retVal = null;

        Session session = getSessionThis();
        Query query = session.getNamedQuery(name);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }

        retVal = (List<T_DOMAIN_CLASS>) query.list();
        return retVal;
    }

    /**
     * @param name
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T_DOMAIN_CLASS> findByNamedQueryAndNamedParams(final String name,
            final Map<String, ? extends Object> params) {
        List<T_DOMAIN_CLASS> retVal = null;

        Session session = getSessionThis();
        Query query = session.getNamedQuery(name);

        for (final Map.Entry<String, ? extends Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        retVal = (List<T_DOMAIN_CLASS>) query.list();

        return retVal;
    }

    /**
     *
     */
    @Override
    public Class<T_DOMAIN_CLASS> getEntityClass() {
        return persistentClass;
    }

    /**
     * Use this inside subclasses as a convenience method.
     *
     * @param criterion
     * @return
     */
    protected List<T_DOMAIN_CLASS> findByCriteria(final Criterion... criterion) {
        return findByCriteria(-1, -1, criterion);
    }

    /**
     * Use this inside subclasses as a convenience method.
     *
     * @param firstResult
     * @param maxResults
     * @param criterion
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<T_DOMAIN_CLASS> findByCriteria(
            final int firstResult,
            final int maxResults,
            final Criterion... criterion) {
        List<T_DOMAIN_CLASS> result = null;
        Session session = getSessionThis();

        Criteria crit = session.createCriteria(getEntityClass());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }

        if (maxResults > 0) {
            crit.setMaxResults(maxResults);
        }

        result = crit.list();

        return result;
    }

    /**
     *
     * @param criterion
     * @return
     */
    protected int countByCriteria(Criterion... criterion) {
        int retVal = -1;

        Session session = getSessionThis();
        Criteria crit = session.createCriteria(getEntityClass());
        crit.setProjection(Projections.rowCount());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        retVal = (Integer) crit.list().get(0);

        return (retVal);
    }

    /**
     *
     */
    @Override
    public void delete(T_DOMAIN_CLASS entity) {
        getSessionThis().delete(entity);
    }

    /**
     *
     */
    @Override
    public int deleteAll() {
        Session session = getSessionThis();

        Query query = session.createQuery("DELETE FROM " + persistentClass.getCanonicalName());
        int retVal = query.executeUpdate();

        logger.warn("just did a delete all of "
                + retVal
                + " rows :"
                + query.getQueryString());

        return (retVal);
    }

    /**
     *
     */
    @Override
    public T_DOMAIN_CLASS saveOrUpdate(final T_DOMAIN_CLASS entity) {
        T_DOMAIN_CLASS retVal = null;
        Session session = getSessionThis();

        session.saveOrUpdate(entity);
        retVal = entity;
        return retVal;
    }

    @Override
    public T_DOMAIN_CLASS merge(T_DOMAIN_CLASS entity) {
        T_DOMAIN_CLASS retVal = null;
        Session session = getSessionThis();

        retVal = (T_DOMAIN_CLASS) session.merge(entity);
        if (retVal == null) {
            logger.fatal("merged entity == NULL"
                    + ReflectionToStringBuilder.toString(entity));
        }

        return retVal;
    }
}
