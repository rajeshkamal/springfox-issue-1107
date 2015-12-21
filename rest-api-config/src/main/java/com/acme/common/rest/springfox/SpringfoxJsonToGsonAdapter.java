package com.acme.common.rest.springfox;

import java.lang.reflect.Type;

import springfox.documentation.spring.web.json.Json;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SpringfoxJsonToGsonAdapter implements JsonSerializer<Json> {

    public JsonElement serialize(Json json, Type type, JsonSerializationContext context) {
        final JsonParser parser = new JsonParser();
        return parser.parse(json.value());
    }
}
