package zdc.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.Date;

@SpringBootApplication
@EnableRabbit
@ConfigurationPropertiesScan(basePackages={"zdc.integration"})
@Slf4j
public class IntegrationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        log.info("OK");

    }





}
