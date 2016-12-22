package com.siaworld.common.config;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

	@Bean
	public ContentNegotiatingViewResolver viewResolver(){
		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		viewResolver.setContentNegotiationManager(this.contentNegotiationManager());
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrefixJson(false);
		List<View> defaultViews = new LinkedList<View>();
		viewResolver.setDefaultViews(defaultViews);
		return viewResolver;
		
	}
	
	private ContentNegotiationManager contentNegotiationManager(){
		Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>();
		mediaTypes.put("json", MediaType.APPLICATION_JSON);
		PathExtensionContentNegotiationStrategy cns = new PathExtensionContentNegotiationStrategy(mediaTypes);
		ContentNegotiationManager cnm = new ContentNegotiationManager(cns);
		
		return cnm;
	
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry reg){
		//to do something
	}
}
