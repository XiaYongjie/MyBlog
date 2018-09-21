package com.hcyq.jblog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/common.properties")
public class CommonPropertiesEnv {
    @Value("${error.no.param}")
    private String errorNoParam;

    public String getErrorNoParam() {
        return errorNoParam;
    }

    public void setErrorNoParam(String errorNoParam) {
        this.errorNoParam = errorNoParam;
    }
}
