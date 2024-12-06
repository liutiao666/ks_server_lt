package com.zmkj.elasticsearch.dto;

import lombok.Data;

import java.util.List;

@Data
public class ElasticSearchDto {
    private String indexName;
    private List<SearchQuery> searchQueryList;

}
