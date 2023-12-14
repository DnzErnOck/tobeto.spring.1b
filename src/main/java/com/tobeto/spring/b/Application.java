package com.tobeto.spring.b;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//localhost:8080/api/persons
//Annotation
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //springi devreye alır
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}