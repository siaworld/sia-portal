package com.siaworld.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"com.siaworld"})
@SpringBootApplication
public class SiaPortalApplication {
	private static SpringApplication siaPortalApplication;
	private static ApplicationContext ctx;
	
	
	public static void main(String[] args) {
		Object[] configArray = {SiaPortalApplication.class};
		siaPortalApplication = new SpringApplication(configArray);
		ctx = siaPortalApplication.run(args);
		
	}
	
	public static SpringApplication getsiaPortalApplication(){
		return siaPortalApplication;
	}
	
	public static ApplicationContext getCtx(){
		return ctx;
	}
}
