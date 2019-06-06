package com.ydk.invoice.htmlbean;

import com.geccocrawler.gecco.spring.SpringPipelineFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigure {

    @Bean
    public SpringPipelineFactory springPipelineFactory() {
        return new SpringPipelineFactory();
    }

}