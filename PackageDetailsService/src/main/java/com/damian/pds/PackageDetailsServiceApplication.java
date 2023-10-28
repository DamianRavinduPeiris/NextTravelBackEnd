package com.damian.pds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PackageDetailsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackageDetailsServiceApplication.class, args);
    }

}
