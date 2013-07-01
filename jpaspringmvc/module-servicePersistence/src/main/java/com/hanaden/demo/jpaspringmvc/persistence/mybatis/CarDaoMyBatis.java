package com.hanaden.demo.jpaspringmvc.persistence.mybatis;

import com.hanaden.demo.jpaspringmvc.domain.CarVo;
import com.hanaden.demo.jpaspringmvc.domain.CarVoBase;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 *
 * @author hanasaki
 */
public interface CarDaoMyBatis {

    //=====================
    @Select("SELECT * FROM CAR")
    public List<CarVoBase> getCars_();

    //=====================
    @Select("SELECT * FROM CAR WHERE id = #{ID}")
    public CarVoBase getCar_(@Param("ID") Long carId);

    //=====================
    @Update("INSERT INTO CAR (company,model,price) VALUES (#{company},#{model},#{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id", flushCache = true)
    public int save_(CarVo car);
    
    //=====================
    @Delete("DELETE FROM CAR WHERE id = #{ID}")
    @Options(flushCache = true)
    public int delete_(@Param("ID") Long carId);
}
