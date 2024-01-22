package org.zerock.b01_2;
//
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class B012Application {

    public static void main(String[] args) {
        SpringApplication.run(B012Application.class, args);
    }
}
