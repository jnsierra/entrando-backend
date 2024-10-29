package co.com.entrando.business.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class FeignConfigInterceptor implements RequestInterceptor {
    private static final  String AUTHORIZATION_HEADER_NAME = "authorization";
    private final HttpServletRequest request;
    @Autowired
    public FeignConfigInterceptor(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = request.getHeader(AUTHORIZATION_HEADER_NAME);
        requestTemplate.header(AUTHORIZATION_HEADER_NAME,token);
    }
}
