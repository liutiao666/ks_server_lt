package com.zmkj.mapper.clickhouse;

import com.zmkj.entity.clickhouse.WebSendTab;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WebSendTabDao {

    void addOne(WebSendTab webSendTab);

    List<WebSendTab> search(@Param("recordKey") String recordKey, @Param("tagKey") String tagKey);
}
