package com.hanaden.demo.jpaspringmvc.webservice;

import com.hanaden.demo.jpaspringmvc.domain.CarVoBase;
import com.hanaden.demo.jpaspringmvc.service.business.TestBusinessService;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author hanasaki
 */
//@WebService()
@XmlSeeAlso({CarVoBase.class})
public interface ParkingLotWebserviceREST extends TestBusinessService {
}