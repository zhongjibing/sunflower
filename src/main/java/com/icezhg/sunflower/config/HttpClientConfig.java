package com.icezhg.sunflower.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongjibing on 2023/02/19.
 */
@Configuration
public class HttpClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate =
                new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        restTemplate.getMessageConverters().add(new TextMappingJackson2HttpMessageConverter());
        restTemplate.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        AnnotationAwareOrderComparator.sort(restTemplate.getInterceptors());
        return restTemplate;
    }

    private static class TextMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public TextMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            mediaTypes.add(MediaType.TEXT_HTML);
            setSupportedMediaTypes(mediaTypes);
        }
    }

    private static class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor, Ordered {
        private static final Logger log =
                LoggerFactory.getLogger(LoggingClientHttpRequestInterceptor.class.getSimpleName());

        private final ThreadLocal<StopWatch> watch = new ThreadLocal<>();

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                throws IOException {
            watch.set(new StopWatch());
            watch.get().start();

            if (log.isDebugEnabled()) {
                log.debug("intercept[0]: [{}] {}", request.getMethod(), request.getURI());
            }

            ClientHttpResponse response = null;
            try {
                response = execution.execute(request, body);
            } finally {
                watch.get().stop();

                if (log.isDebugEnabled()) {
                    int status = response != null ? response.getStatusCode().value() : -1;
                    log.debug("intercept[1]: [{}] {}, response status: {}, duration: {} ms.",
                            request.getMethod(), request.getURI(), status, watch.get().getTotalTimeMillis());
                }
            }

            return response;
        }


        @Override
        public int getOrder() {
            return Ordered.HIGHEST_PRECEDENCE;
        }
    }

}
