package com.zmkj.elasticsearch.controller;

import com.zmkj.elasticsearch.dto.ElasticSearchDto;
import com.zmkj.elasticsearch.service.ElasticSearchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/es")
public class ElasticSearchController {

    @Autowired
    private ElasticSearchServiceImpl elasticsearchService;

    @PostMapping("/save")
    public String saveDocument(@RequestBody Map<String, Object> document) {
        String indexName = "dynamic_index_name"; // 替换为您的索引名称
        try {
            elasticsearchService.save(indexName, document);
            return "Document saved successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return "Document saved failed";
        }
    }


    @PostMapping("/search")
    public String searchEs(@RequestBody ElasticSearchDto searchDto) {
        try {
            return elasticsearchService.search(searchDto);
        } catch (Exception e) {
            log.error("error:{}", e.getMessage(), e);
            return "failed";
        }
    }
}
