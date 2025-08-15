package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RLEMain {
    public static void main(String[] args) {
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "RLE");
        Test RLETest = new Tester(RLE::new);
        RLETest.run();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "RLE с оптимизацией");
        Test RLEOptimizationTest = new Tester(RLEOptimization::new);
        RLEOptimizationTest.run();

        SpringApplication.run(RLEMain.class, args);
    }
}