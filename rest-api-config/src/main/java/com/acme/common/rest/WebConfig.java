package com.acme.common.rest;

import java.text.DateFormat;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.hateoas.HypermediaHttpMessageConverterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.spring.web.json.Json;

import com.acme.common.rest.springfox.SpringfoxJsonToGsonAdapter;
import com.acme.common.rest.version.ApiVersion;
import com.acme.common.rest.version.ApiVersionConverter;
import com.acme.common.rest.version.ClassExclusionStratergy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
@Import({ LocaleConfig.class })
@EnableAutoConfiguration(exclude = { HypermediaHttpMessageConverterConfiguration.class })
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // below two converters based on GSON are needed for API versioning
        converters.add(new ApiVersionConverter(ApiVersion.VERSION_1_0));
        converters.add(new ApiVersionConverter(ApiVersion.VERSION_2_0));
        converters.add(gsonHttpMessageConverter());
    }

    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(buildGson());
        return converter;
    }

    protected Gson buildGson() {
        return new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL)
                .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter())
                .setExclusionStrategies(new ClassExclusionStratergy(UriTemplate.class))
                .setPrettyPrinting().create();
    }

}