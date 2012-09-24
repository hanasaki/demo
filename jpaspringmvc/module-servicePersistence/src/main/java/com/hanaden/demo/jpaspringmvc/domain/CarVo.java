package com.hanaden.demo.jpaspringmvc.domain;

import com.sun.xml.bind.AnyTypeAdapter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author hanasaki
 */
@XmlRootElement
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface CarVo {

    String getManufacturer();

    long getId();

    void setId(Long id);

    String getModel();

    long getYear();

    @NotNull(message = "ERROR : Manufacturer is NULL")
    @Size(min = 2, max = 15, message = "ERROR : Manufacturer must be 2-15 characters")
    void setManufacturer(String company);

    @NotNull(message = "ERROR : Model is NULL")
    @Size(min = 2, max = 15, message = "ERROR : Model must be 2-15 characters")
    void setModel(String model);

    void setYear(long year);
}
