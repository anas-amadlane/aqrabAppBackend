package com.example.nvpproject;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NvpProjectApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(NvpProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //customerService.saveCustomer();
    }
}
