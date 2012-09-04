package com.hanaden.demo.jpaspringmvc.domain;

/**
 *
 * @author hanasaki
 */
public interface CarVo {

    String getCompany();

    long getId();

    void setId(Long id);

    String getModel();

    long getPrice();

    void setCompany(String company);

    void setModel(String model);

    void setPrice(long price);
}
