package com.monederobingo.points_configuration;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class App
{
    public static void main(String[] args)
    {
        run(App.class, args);
    }
}
