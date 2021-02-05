package com.spiashko.rfetchexample.swagger;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.SpringDocAnnotationsUtils;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class JsonIgnorePropertiesWorkAroundOperationCustomizer implements OperationCustomizer {

    private final RfetchSpecOpenApiCustomiserRegistry registry;

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {

        MethodParameter reqBodyParam = Arrays.stream(handlerMethod.getMethodParameters())
                .filter(methodParameter -> methodParameter.hasParameterAnnotation(RequestBody.class))
                .findFirst()
                .orElse(null);

        if (reqBodyParam != null) {
            JsonView jsonView = reqBodyParam.getParameterAnnotation(JsonView.class);
            Class<?> clazz = getEntityClass(reqBodyParam);
            addCustomiser(clazz, jsonView);
        }

        MethodParameter returnParam = handlerMethod.getReturnType();

        Class<?> returnType = getEntityClass(returnParam);
        JsonView jsonView = handlerMethod.getMethodAnnotation(JsonView.class);
        addCustomiser(returnType, jsonView);

        return operation;
    }

    private void addCustomiser(Class<?> clazz, JsonView jsonView) {
        registry.addCustomiser(
                (OpenAPI openApi) -> {
                    Schema<?> schema = SpringDocAnnotationsUtils.extractSchema(openApi.getComponents(), clazz, jsonView);
                    String schemaRef = schema.get$ref();
                    String schemaName = schemaRef.substring(schemaRef.lastIndexOf('/') + 1);
                    //regenerate schema to leverage JsonIgnoreProperties issue
                    openApi.getComponents().getSchemas().remove(schemaName);
                    SpringDocAnnotationsUtils.extractSchema(openApi.getComponents(), clazz, jsonView);
                }
        );
    }

    private Class<?> getEntityClass(MethodParameter parameter) {
        if (parameter.getGenericParameterType().equals(parameter.getParameterType())) {
            return parameter.getParameterType();
        }
        return (Class<?>) ((ParameterizedType) parameter.getGenericParameterType()).getActualTypeArguments()[0];
    }

}