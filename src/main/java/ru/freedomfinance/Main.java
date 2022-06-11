package ru.freedomfinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.freedomfinance.model.Usd;
import ru.freedomfinance.repos.UsdRepository;

import java.time.LocalTime;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

@SpringBootApplication(scanBasePackages = {"ru.freedomfinance.repos", "ru.freedomfinance.core", "ru.freedomfinance.model", "ru.freedomfinance.service", "ru.freedomfinance"})
@EnableJpaRepositories(basePackages = "ru.freedomfinance.repos")
@ComponentScan(basePackages = "ru.freedomfinance.*")
@EntityScan("ru.freedomfinance.*")

public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}
