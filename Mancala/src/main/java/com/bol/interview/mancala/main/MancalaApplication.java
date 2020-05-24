package com.bol.interview.mancala.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bol.interview.mancala")
public class MancalaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MancalaApplication.class, args);
    }

}
