package com.acme.vdi;

import org.springframework.context.annotation.Configuration;

import com.acme.common.rest.springfox.SwaggerConfig;

@Configuration
public class VDISwaggerConfig extends SwaggerConfig {

    @Override
    protected String getServiceName() {
        return "VDI Service";
    }

    @Override
    protected String getServletContextPath() {
        return "/domain";
    }

}