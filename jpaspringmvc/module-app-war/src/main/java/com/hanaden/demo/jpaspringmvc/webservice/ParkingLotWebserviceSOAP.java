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

    /**
     * Web service operation
     */
//    @WebMethod(operationName = "operation")
//    public String operation(@WebParam(name = "parameter") String parameter);
}