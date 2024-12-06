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
        // 检查 document 中是否有 id 键
        if (!document.containsKey("id")) {
            throw new IllegalArgumentException("Document must contain an 'id' field.");
        }

        String id = document.get("id").toString();

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(id)
                .withObject(document)
                .build();

        // 存储文档
        try {
            elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of(index));
        } catch (Exception e) {
            // 处理异常
            throw new RuntimeException("Failed to save document to index: " + index, e);
        }
    }
}
