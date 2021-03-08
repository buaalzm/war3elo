package com.war3elo;


import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.war3elo.mapper")
public class War3eloApplication {

    public static void main(String[] args) {
        SpringApplication.run(War3eloApplication.class, args);
    }

}
