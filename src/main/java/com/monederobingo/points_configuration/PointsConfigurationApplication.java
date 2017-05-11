package com.monederobingo.points_configuration;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PointsConfigurationApplication
{

    public static void main(String[] args)
    {
        run(PointsConfigurationApplication.class, args);
    }

    @RequestMapping(value = "/")
    public String sayHello()
    {
        return "Points Configuration!";
    }
}
