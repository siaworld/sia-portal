package com.siaworld.common.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.PlatformTransactionManager;

@Lazy
@EnableAspectJAutoProxy
@EnableTransactionManagement
@MapperScan(basePackages={"com.siaworld.repository"})
@Configuration
public class DBConfig implements TransactionManagementConfigurer{

	@Autowired
	private ApplicationContext ctx;
	@Autowired
	private DataSource datasource;
	
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
		return new TomcatEmbeddedServletContainerFactory();
	}
	
	@Bean
	public DataSource dataSource() throws Exception {
		DataSourceFactory dsFactory = new DataSourceFactory();
		Properties prop = new Properties();
		prop.setProperty("url", "localhost:3306");
		prop.setProperty("username", "root");
		prop.setProperty("password", "1234");
		prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
		return dsFactory.createDataSource(prop);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(datasource);
		sessionFactory.setConfigLocation(ctx.getResource("classpath:/mybatis-config.xml"));
		sessionFactory.setMapperLocations(ctx.getResources("classpath:/mappers/*.xml"));
		return sessionFactory.getObject();
	}
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager(){
		return new DataSourceTransactionManager(datasource);
		
	}
	
//	@Bean
//	public RepositoryAspect repositoryAspect(){
//		return new RepositoryAspect();
//	}
 }
