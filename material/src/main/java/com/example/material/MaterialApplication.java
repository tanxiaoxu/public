package com.example.material;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("mapping")
public class MaterialApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaterialApplication.class, args);
    }

}
