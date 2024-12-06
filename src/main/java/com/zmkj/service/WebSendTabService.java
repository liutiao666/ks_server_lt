package com.zmkj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zmkj.entity.clickhouse.WebSendTab;

import java.sql.SQLException;
import java.util.List;

public interface WebSendTabService {

    void addOne(WebSendTab webSendTab) throws JsonProcessingException, SQLException;

    List<WebSendTab> serachAll(String recordKey, String tagKey);
}
