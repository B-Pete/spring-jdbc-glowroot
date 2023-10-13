package com.mkyong;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

// https://spring.io/guides/gs/relational-data-access/
// https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#jdbc
// https://github.com/mkyong/spring-boot

@SpringBootApplication
public class StartApplication {

	@Bean
	public LobHandler lobHandler() {
		return new DefaultLobHandler();
	}

	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource() {
		org.apache.tomcat.jdbc.pool.DataSource ds = DataSourceBuilder.create()
				.type(org.apache.tomcat.jdbc.pool.DataSource.class).build();
		ds.setUrl("jdbc:h2:mem:AZ;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
		ds.setDriverClassName("org.h2.Driver");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
	}

	//java -jar -javaagent:src/main/glowroot14/glowroot.jar target/spring-jdbc-glowroot-1.0.jar
	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

}