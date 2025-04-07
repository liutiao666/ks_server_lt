package com.zmkj.mapper.clickhouse;

import com.zmkj.dto.AssistPerformanceDto;
import com.zmkj.entity.clickhouse.AssistPerformance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AssistPerformanceDao {

    void addOne(AssistPerformanceDto assistPerformance);

    List<AssistPerformance> searchAll();

    void batchInsertCK(List<AssistPerformance> assistPerformances);
}
