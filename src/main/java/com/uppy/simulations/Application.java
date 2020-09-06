package com.uppy.simulations;


import com.uppy.simulations.utils.PusherUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        PusherUtil.startClient(Arrays.asList(new PusherUtil.EventChannelConfig(
                "user-1", Arrays.asList("driver-not-found")
        )));
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder
                                                         builder) {
        return builder.sources(Application.class);
    }
}
