package com.hanaden.demo.jpaspringmvc.webservice;

import com.hanaden.demo.jpaspringmvc.domain.CarVoBase;
import com.hanaden.demo.jpaspringmvc.service.business.TestBusinessService;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author hanasaki
 */
@WebService()
@XmlSeeAlso({CarVoBase.class})
public interface ParkingLotWebserviceSOAP extends TestBusinessService {
}