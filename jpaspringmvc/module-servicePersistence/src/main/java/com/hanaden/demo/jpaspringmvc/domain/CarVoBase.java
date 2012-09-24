package com.hanaden.demo.jpaspringmvc.domain;

import java.io.Serializable;

/**
 *
 * @author hanasaki
 */
public class CarVoBase implements CarVo, Serializable {

    private long id;
    private String company;
    private String model;
    private long price;

    /**
     * @return the id
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the company
     */
    @Override
    public String getManufacturer() {
        return company;
    }

    /**
     * @param company the company to set
     */
    @Override
    public void setManufacturer(String company) {
        this.company = company;
    }

    /**
     * @return the model
     */
    @Override
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    @Override
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the price
     */
    @Override
    public long getYear() {
        return price;
    }

    /**
     * @param price the price to set
     */
    @Override
    public void setYear(long price) {
        this.price = price;
    }
}
