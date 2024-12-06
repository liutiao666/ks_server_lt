package com.zmkj.elasticsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ElasticSearchServiceImpl {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void save(String index, Map<String, Object> document) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(document.get("id").toString())
                .withObject(document)
                .build();

        // 存储文档
        elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of(index));
    }
}
