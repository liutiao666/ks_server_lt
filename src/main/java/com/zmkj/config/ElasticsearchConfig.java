package com.zmkj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.rest.uris}")
    private String elasticsearchClusterNodes;

    @Value("${spring.elasticsearch.rest.username}")
    private String elasticsearchUsername;

    @Value("${spring.elasticsearch.rest.password}")
    private String elasticsearchPassword;

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticsearchClusterNodes)
                .withBasicAuth(elasticsearchUsername, elasticsearchPassword)
                .build();

        return new ElasticsearchRestTemplate(RestClients.create(clientConfiguration).rest());
    }
}