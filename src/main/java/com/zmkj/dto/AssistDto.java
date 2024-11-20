package com.zmkj.dto;

import lombok.Data;

import java.util.Arrays;

@Data
public class AssistDto {

    private String[] dataList;

    public AssistDto(String[] dataList) {
        this.dataList = Arrays.stream(dataList).map((data) -> {
            if (data.startsWith("\"") && data.endsWith("\"")) {
                data = data.substring(1, data.length() - 1);
            }
            return data;
        }).toArray(String[]::new);
    }


    @Override
    public String toString() {
        return "Assist{" +
                "dataList=" + Arrays.toString(dataList) +
                '}';
    }
}
