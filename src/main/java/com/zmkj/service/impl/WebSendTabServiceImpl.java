package com.zmkj.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmkj.dto.WebSendTabDto;
import com.zmkj.entity.clickhouse.WebSendTab;
import com.zmkj.mapper.clickhouse.WebSendTabDao;
import com.zmkj.service.WebSendTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class WebSendTabServiceImpl implements WebSendTabService {

    @Autowired
    WebSendTabDao webSendTabDao;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DataSource clickhouseDataSource;


    @Override
    public void addOne(WebSendTab webSendTab) throws JsonProcessingException, SQLException {
//        insertBysql(webSendTab);
//        WebSendTab webSendTab = new WebSendTab();
//        webSendTab.setEnvId(webSendTabDto.getEnvId());
//        webSendTab.setAppName(webSendTabDto.getAppName());
//        webSendTab.setAppVer(webSendTabDto.getAppVer());
//        webSendTab.setLogDate(webSendTabDto.getLogDate());
//        webSendTab.setRecords(objectMapper.writeValueAsString(webSendTabDto.getRecords()));
//        webSendTab.setTags(transMapStr(webSendTabDto.getTags()));
        webSendTabDao.addOne(webSendTab);
    }

    private void insertBysql(WebSendTab webSendTab) throws SQLException, JsonProcessingException {
        String sql = "INSERT INTO db_13.web_send_tab (envId, appName, appVer, records, logDate, tags) VALUES (?, ? ,? ,? ,? ,?)";
        Connection connection = clickhouseDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, webSendTab.getEnvId());
        preparedStatement.setString(2, webSendTab.getAppName());
        preparedStatement.setString(3, webSendTab.getAppVer());
        preparedStatement.setString(4, objectMapper.writeValueAsString(webSendTab.getRecords()));
        preparedStatement.setString(5, webSendTab.getLogDate());
        preparedStatement.setString(6, objectMapper.writeValueAsString(webSendTab.getTags()));
        boolean flag = preparedStatement.execute();
        System.out.println(flag);
    }

    @Override
    public List<WebSendTab> serachAll(String recordKey, String tagKey) {
        List<WebSendTab> webSendTabs = webSendTabDao.search(recordKey, tagKey);
        return webSendTabs;
    }
}
