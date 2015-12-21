package com.acme.common.rest.springfox;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;

@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public abstract class SwaggerConfig {

    @Value("${springfox.documentation.swagger.v2.path}")
    private String swagger2Endpoint;
    @Autowired
    private TypeResolver typeResolver;

    protected abstract String getServiceName();

    protected abstract String getServletContextPath();

    // DeferredResult<List<T>> to List<T>
    public AlternateTypeRule getAlternateTypeRule(Type sourceType, Type sourceGenericType,
            Type sourceSubGenericType, Type targetType, Type targetGenericType) {
        return AlternateTypeRules.newRule(
                typeResolver.resolve(sourceType,
                        typeResolver.resolve(sourceGenericType, sourceSubGenericType)),
                typeResolver.resolve(targetType, targetGenericType));
    }

    // Collection<T> to List<T>
    public AlternateTypeRule getAlternateTypeRule(Type sourceType, Type sourceGenericType,
            Type targetType, Type targetGenericType) {
        return AlternateTypeRules.newRule(typeResolver.resolve(sourceType, sourceGenericType),
                typeResolver.resolve(targetType, targetGenericType));
    }

    // Resources<T> to Resources
    public AlternateTypeRule getAlternateTypeRule(Type sourceType, Type sourceGenericType,
            Type targetType) {
        return AlternateTypeRules.newRule(typeResolver.resolve(sourceType, sourceGenericType),
                typeResolver.resolve(targetType));
    }

    @Bean
    public Docket confDocContext() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .alternateTypeRules(
                        getAlternateTypeRule(Collection.class, WildcardType.class, List.class,
                                WildcardType.class),
                        getAlternateTypeRule(DeferredResult.class, List.class, WildcardType.class,
                                List.class, WildcardType.class))
                .ignoredParameterTypes(PagedResourcesAssembler.class, Pageable.class)
                .directModelSubstitute(MessageSourceResolvable.class, String.class).select()
                .paths(Predicates.not(PathSelectors.regex("/error"))).build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(getServiceName(), getServiceName(), "1.0", null, "acme@acme.com",
                null, null);
    }

}