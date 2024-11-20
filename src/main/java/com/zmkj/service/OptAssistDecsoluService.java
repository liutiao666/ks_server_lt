package com.zmkj.service;

import com.zmkj.entity.mysql.OptAssistDecsolu;

import java.util.List;

public interface OptAssistDecsoluService {

    List<OptAssistDecsolu> searchDecsolus(String status);
}
