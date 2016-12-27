package com.siaworld.common.rest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestOperation {

	private RestTemplate template;
	private MediaType content;
	private MediaType[] accepts;
	
	public RestOperation(RestTemplate template, MediaType content, MediaType[] accepts){
		this.template = template;
		this.content = content;
		this.accepts = accepts;
	}
	
	public <R> R get (RestTemplate template, String url, HttpHeaders headers, Object params, Class<R> response) {
		return exchange(url, HttpMethod.GET, headers, params, response );
	}
	
	public <R> R post (RestTemplate template, String url, HttpHeaders headers, Object params, Class<R> response) {
		return exchange(url, HttpMethod.POST, headers, params, response);
	}
	
	public <R> R put (RestTemplate template, String url, HttpHeaders headers, Object params, Class<R> response) {
		return exchange(url, HttpMethod.PUT, headers, params, response);
	}

	public <R> R delete (RestTemplate template, String url, HttpHeaders headers, Object params, Class<R> response) {
		return exchange(url, HttpMethod.DELETE, headers, params, response);
	}
	
	private <R> R exchange(String url, HttpMethod method, HttpHeaders headers, Object params, Class<R> response) {
		URI uri = null;
		try {
			uri = new URL(url).toURI();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(method == HttpMethod.POST || method == HttpMethod.PUT){
			if(content != null){
				if(headers ==null)
					headers = new HttpHeaders();
				headers.setContentType(content);
			}
		}
		if(accepts != null ){
			if(headers == null)
				headers = new HttpHeaders();
			headers.setAccept(new ArrayList<MediaType>(Arrays.asList(accepts)));
		}
		
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(params, headers);
		
		ResponseEntity<R> responseEntity;
		if(uri != null){
			responseEntity = template.exchange(uri,  method, requestEntity, response);						
			return responseEntity.getBody();
		}
		return null;
		
	}
}
