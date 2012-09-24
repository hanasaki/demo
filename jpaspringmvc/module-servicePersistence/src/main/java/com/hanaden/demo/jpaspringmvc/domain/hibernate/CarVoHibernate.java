package com.hanaden.demo.jpaspringmvc.domain.hibernate;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.domain.CarVoBase;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hanasaki
 */
@Entity(name = "CarVoHibernate")
@Table(name = "Car")
public class CarVoHibernate extends CarVoBase {

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return (super.getId());
    }
    
    @Override
    public String getManufacturer() {
        return (super.getManufacturer());
    }

    @Override
    public void setManufacturer(String company) {
        super.setManufacturer(company);
    }

    @Column(name = "model")
    @Override
    public String getModel() {
        return (super.getModel());
    }

    @Override
    public void setModel(String model) {
        super.setModel(model);
    }

    @Column(name = "price")
    @Override
    public long getYear() {
        return (super.getYear());
    }

    @Override
    public void setYear(long price) {
        super.setYear(price);
    }
}
