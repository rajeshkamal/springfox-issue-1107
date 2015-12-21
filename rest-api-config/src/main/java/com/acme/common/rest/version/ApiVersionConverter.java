package com.acme.common.rest.version;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.hateoas.UriTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import springfox.documentation.spring.web.json.Json;

import com.acme.common.rest.springfox.SpringfoxJsonToGsonAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApiVersionConverter extends GsonHttpMessageConverter {

    private double apiVersion;
    protected static final String VERSION_HEADER = "Version";

    public ApiVersionConverter(double apiVersion) {
        super();
        this.apiVersion = apiVersion;
        Map<String, String> params = new HashMap<String, String>();
        params.put(VERSION_HEADER, Double.toString(apiVersion));
        params.put("charset", "UTF-8");
        super.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json", params),
                new MediaType("application", "*+json", params)));
        super.setGson(buildGson());
    }

    protected Gson buildGson() {
        return new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL)
                .setVersion(apiVersion)
                .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter())
                .setExclusionStrategies(new ClassExclusionStratergy(UriTemplate.class))
                .setPrettyPrinting().create();
    }

    protected boolean containsVersion(MediaType mediaType) {
        if (apiVersion != 0 && mediaType != null
                && mediaType.getParameters().containsKey(VERSION_HEADER)) {
            String version = mediaType.getParameter(VERSION_HEADER);
            return ApiVersion.versions.contains(Double.valueOf(version))
                    && apiVersion == Double.valueOf(version);
        }
        return false;
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return containsVersion(mediaType) && canRead(mediaType);
    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        return containsVersion(mediaType) && canRead(mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return containsVersion(mediaType) && canWrite(mediaType);
    }
}