package com.hanaden.demo.jpaspringmvc.persistence.mybatis;

import java.util.ArrayList;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 * Generic implementation of DAO pattern
 *
 * @author http://www.ibm.com/developerworks/java/library/j-genericdao.html
 */
public interface GenerickDaoMyBatisInterface<T, PK> {

    //get obj of type T by the primary key 'id'	
    public T get(PK id) throws PersistenceException;

    //get obj of type T by the 'name' field, if one exists for that table
    public T getByName(String name) throws PersistenceException;

    //get all objects of type T
    public ArrayList<T> getAll() throws PersistenceException;

    //insert an object of type T into the database
    public int create(T objInstance) throws PersistenceException;

    //update an object of type T    
    int update(T transientObject) throws PersistenceException; 

    //delete an object of type T
    int delete(PK id) throws PersistenceException;
}
