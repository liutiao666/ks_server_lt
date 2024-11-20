package com.zmkj.entity.mysql;

import lombok.Data;

@Data
public class OptAssistDecsolu {
    private int id;
    private String name;
    private String status;
    private String languageType;
    private String createTime;
    private int createUseId;
    private String createUserName;
    private String updateTime;
    private int updateUseId;
    private String updateUserName;
    private String content;
}
