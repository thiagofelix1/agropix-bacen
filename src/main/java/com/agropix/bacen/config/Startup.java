package com.agropix.bacen.config;

import com.agropix.bacen.adapter.out.TransacaoPixPortOut;
import com.agropix.bacen.application.port.out.DataBasePortOut;
import com.agropix.bacen.application.service.TransacaoService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class Startup {

    @Bean
    public RestTemplate setupRestTemplateWithHalfSecondTimeout(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
            .setReadTimeout(Duration.ofMillis(500))
            .build();
    }

    public TransacaoService transacaoServiceProvider(TransacaoPixPortOut transacaoHttpClient, DataBasePortOut dataBasePortOut) {
        return new TransacaoService(dataBasePortOut, transacaoHttpClient);
    }
}
