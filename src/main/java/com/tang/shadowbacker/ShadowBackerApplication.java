package com.tang.shadowbacker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ShadowBackerApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        // 可选：手动设置 System 环境变量（Spring Boot 可自动读取 ${VAR}）
        dotenv.entries().forEach(entry -> {
            if (System.getenv(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });

        SpringApplication.run(ShadowBackerApplication.class, args);
    }
}