package com.zmkj.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class EsConfig {

    @Value("${spring.elasticsearch.rest.uris}")
    private String elasticsearchClusterNodes;

    @Value("${spring.elasticsearch.rest.username}")
    private String elasticsearchUsername;

    @Value("${spring.elasticsearch.rest.password}")
    private String elasticsearchPassword;

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(elasticsearchUsername,elasticsearchPassword));

        SSLContextBuilder sslBuilder = SSLContexts.custom()
                .loadTrustMaterial(null, (x509Certificates, s) -> true);
        final SSLContext sslContext = sslBuilder.build();
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticsearchClusterNodes)
                .withBasicAuth(elasticsearchUsername, elasticsearchPassword)
                .build();

        return new ElasticsearchRestTemplate(RestClients.create(clientConfiguration).rest());
    }
}