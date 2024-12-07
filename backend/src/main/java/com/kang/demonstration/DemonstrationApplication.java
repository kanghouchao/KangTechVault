package com.kang.demonstration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author kanghouchao
 */
@EnableWebSecurity
@SpringBootApplication
public class DemonstrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemonstrationApplication.class, args);
    }

}
