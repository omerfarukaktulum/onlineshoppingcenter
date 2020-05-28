package com.ofa.epttavm.gateway.configuration;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Configuration
public class HystrixFallbackConfiguration {

    @Bean
    public FallbackProvider fallbackProvider(){
        return new FallbackProvider() {
            @Override
            public String getRoute() {
                return "multiplication";
            }

            @Override
            public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
                return new ClientHttpResponse() {
                    @Override
                    public HttpStatus getStatusCode() {
                        return HttpStatus.OK;
                    }

                    @Override
                    public int getRawStatusCode() {
                        return HttpStatus.OK.value();
                    }

                    @Override
                    public String getStatusText() {
                        return HttpStatus.OK.toString();
                    }

                    @Override
                    public void close() {
                    }

                    @Override
                    public InputStream getBody() {
                        return new ByteArrayInputStream("{\" factorA\":\"Sorry, Service is Down! \",\"factorB\":\"?\",\"id\":null}".getBytes());
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.setAccessControlAllowOrigin("*");
                        headers.setAccessControlAllowCredentials(true);
                        return headers;
                    }
                };
            }
        };
    }
}
