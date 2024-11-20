package com.zmkj.mapper.mysql;

import com.zmkj.entity.mysql.OptAssistDecsolu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OptAssistDecsoluDao {

    List<OptAssistDecsolu> searchByStatus(String status);

}
