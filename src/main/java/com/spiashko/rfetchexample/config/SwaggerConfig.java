package com.spiashko.rfetchexample.config;

import com.spiashko.rfetchexample.swagger.RfetchSpecOpenApiCustomiserRegistry;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.SpringDocUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;

@Configuration
public class SwaggerConfig {

    static {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(Specification.class);
    }

    @Bean
    public RfetchSpecOpenApiCustomiserRegistry openApiCustomiserRegistry() {
        return new RfetchSpecOpenApiCustomiserRegistry();
    }

    @Bean
    public OpenApiCustomiser myOpenApiCustomiser(RfetchSpecOpenApiCustomiserRegistry openApiCustomiserRegistry) {
        return (OpenAPI openApi) ->
                openApiCustomiserRegistry.getCustomisers().forEach(customiser -> customiser.customise(openApi));
    }

}
