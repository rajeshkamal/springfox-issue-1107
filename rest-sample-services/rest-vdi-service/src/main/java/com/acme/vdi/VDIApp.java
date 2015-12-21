package com.acme.vdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.acme.common.rest.WebConfig;

/**
 * The Main Application.
 */
@SpringBootApplication
@ComponentScan({ "com.acme.vdi", "com.acme.common" })
public class VDIApp {

    /**
     * The main function for the application.
     *
     * @param args
     *            The arguments to main.
     */
    public static void main(final String[] args) {
        SpringApplication.run(
                new Object[] { VDIApp.class, VDISwaggerConfig.class, WebConfig.class }, args);
    }

}