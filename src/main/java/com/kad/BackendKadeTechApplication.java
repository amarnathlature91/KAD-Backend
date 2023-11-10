package com.kad;

import com.kad.utils.EmailUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class BackendKadeTechApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendKadeTechApplication.class, args);
    }

    @Bean
    public EmailUtils emailUtils() {
        return new EmailUtils();
    }

}
