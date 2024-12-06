package com.zmkj.elasticsearch.service;

import com.zmkj.elasticsearch.dto.ElasticSearchDto;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ElasticSearchServiceImpl {

/*    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;*/
    
    @Autowired
    RestHighLevelClient restHighLevelClient;

    public void save(String index, Map<String, Object> document) throws IOException {
        // 检查 document 中是否有 id 键
/*        if (!document.containsKey("id")) {
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
        }*/
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.source(document);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    public String search(ElasticSearchDto searchDto) throws IOException {
        // 构建查询请求
        SearchRequest searchRequest = new SearchRequest(searchDto.getIndexName());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        searchDto.getSearchQueryList().forEach(searchQuery -> {
            boolQueryBuilder.must(QueryBuilders.matchQuery(searchQuery.getField(), searchQuery.getValue()));
        });
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        // 执行查询
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 处理查询结果
        for (SearchHit hit : searchResponse.getHits()) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
        return "success";
    }
}
