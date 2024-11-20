package com.zmkj.service.impl;

import com.zmkj.mapper.mysql.OptAssistDecsoluDao;
import com.zmkj.entity.mysql.OptAssistDecsolu;
import com.zmkj.service.OptAssistDecsoluService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptAssistDecsoluServiceImpl implements OptAssistDecsoluService {

    @Autowired
    OptAssistDecsoluDao optAssistDecsoluDao;

    @Override
    public List<OptAssistDecsolu> searchDecsolus(String status) {
        return optAssistDecsoluDao.searchByStatus(status);
    }
}
