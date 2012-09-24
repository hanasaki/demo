package com.hanaden.demo.jpaspringmvc.persistence.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicTest {

    private Logger logger = Logger.getLogger(BasicTest.class);
    
    @Before
    public void setup() {
        logger.debug("setup");
    }
    
    @After
    public void teardown() {
    }
    
    @Test()
    public void simpleTest() {

    }
}
