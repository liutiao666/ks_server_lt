package com.zmkj;

import org.apache.http.ssl.SSLContexts;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.net.ssl.*;
import java.io.File;
import java.security.cert.X509Certificate;

@SpringBootApplication
//@EnableScheduling
@MapperScan(basePackages = {"com.zmkj.mapper.mysql", "com.zmkj.mapper.clickhouse"})
public class KsServerApplication {

    static {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            SSLContext sc = SSLContexts.custom()
                    .loadTrustMaterial(new File("D:\\myData\\ks_server_lt\\src\\main\\resources\\mykeystore.jks"), "123456".toCharArray())
                    .build();
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(KsServerApplication.class, args);
    }
}
