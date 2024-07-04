package com.bezkoder.spring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

import io.r2dbc.spi.ConnectionFactory;

@EnableWebFlux
//@EnableR2dbcRepositories
@SpringBootApplication
@ComponentScan(basePackages = "com.bezkoder.spring.webflux")
public class SpringBootWebfluxExampleApplication {


  public static void main(String[] args) {
    SpringApplication.run(SpringBootWebfluxExampleApplication.class, args);
  }

}
