package com.siaworld.common.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 300;
	private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 100;
	private static final int DEFAULT_READ_TIMEOUT_MSEC = 10000;
	private static final int DEFAULT_CONNECTION_TIMEOUT_MSEC = 10000;
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate(httpRequestFactory());
	}
	
	@Bean
	public ClientHttpRequestFactory httpRequestFactory(){
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient());
		factory.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT_MSEC);
		factory.setReadTimeout(DEFAULT_READ_TIMEOUT_MSEC);
		return factory;
		
	}
	
	@Bean
	public HttpClient httpClient(){
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
		connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
		CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).build();
		return httpClient;
		
		
	}
	
}
