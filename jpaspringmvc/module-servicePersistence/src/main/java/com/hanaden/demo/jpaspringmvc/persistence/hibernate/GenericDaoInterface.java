package com.hanaden.demo.jpaspringmvc.persistence.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fred Bloom
 * @param <T_DOMAIN_CLASS>
 * @param <PK_CLASS>
 */
public interface GenericDaoInterface<T_DOMAIN_CLASS, PK_CLASS extends Serializable> {

    enum LOCK_MODE {

        NONE,
        READ,
        WRITE
    }
    //~ Methods ----------------------------------------------------------------

    /**
     * Get the Class of the entity.
     *
     * @return the class
     */
    Class<T_DOMAIN_CLASS> getEntityClass();

    /**
     *
     */
//    public void flush();
    /**
     * Find an entity by its primary key
     *
     * @param id the primary key
     * @return the entity
     */
    T_DOMAIN_CLASS findByPk(final PK_CLASS id);

    /**
     *
     * @param id
     * @param lockMode
     * @return
     */
    public T_DOMAIN_CLASS findByPk(final PK_CLASS id, final LOCK_MODE lockOptions);

    /**
     * Load all entities.
     *
     * @return the list of entities
     */
    List<T_DOMAIN_CLASS> findAll();

    /**
     * Find entities based on an example.
     *
     * @param exampleInstance the example
     * @return the list of entities
     */
    List<T_DOMAIN_CLASS> findByExample(final T_DOMAIN_CLASS exampleInstance);

    /**
     * Find using a named query.
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    List<T_DOMAIN_CLASS> findByNamedQuery(
            final String queryName,
            Object... params);

    /**
     * Find using a named query.
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    List<T_DOMAIN_CLASS> findByNamedQueryAndNamedParams(
            final String queryName,
            final Map<String, ? extends Object> params);

    /**
     * Count all entities.
     *
     * @return the number of entities
     */
    int countAll();

    /**
     * Count entities based on an example.
     *
     * @param exampleInstance the search criteria
     * @return the number of entities
     */
    int countByExample(final T_DOMAIN_CLASS exampleInstance);

    /**
     * save an entity. This can be either a INSERT or UPDATE in the database.
     *
     * @param entity the entity to save
     *
     * @return the saved entity
     */
    T_DOMAIN_CLASS saveOrUpdate(final T_DOMAIN_CLASS entity);

    /**
     *
     * @param entity
     * @return
     */
    T_DOMAIN_CLASS merge(T_DOMAIN_CLASS entity);

    /**
     * delete an entity from the database.
     *
     * @param entity the entity to delete
     */
    void delete(final T_DOMAIN_CLASS entity);

    /**
     *
     */
    public int deleteAll();
}
