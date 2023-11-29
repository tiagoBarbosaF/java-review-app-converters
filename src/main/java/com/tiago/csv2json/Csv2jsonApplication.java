package com.tiago.csv2json;

import com.tiago.csv2json.start.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Csv2jsonApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Csv2jsonApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Main.start();
    }
}
