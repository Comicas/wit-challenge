package com.renato.wit_challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Filter responsible for generating unique request identifiers and propagating them through MDC.
 * 
 * @author Renato
 */

@Component
public class requestID implements Filter {
    
    private static final Logger logger = LoggerFactory.getLogger(requestID.class);
    private static final String REQUEST_ID_HEADER = "X-Request-ID";
    private static final String REQUEST_ID_MDC_KEY = "requestId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        //Generate unique request identifier
        String requestId = UUID.randomUUID().toString();
        
        //Add to MDC for automatic propagation
        MDC.put(REQUEST_ID_MDC_KEY, requestId);
        
        //Add to response header
        httpResponse.setHeader(REQUEST_ID_HEADER, requestId);
        
        //Add to request attributes
        httpRequest.setAttribute(REQUEST_ID_MDC_KEY, requestId);
        
        logger.info("Generated Request-ID for {} {}", httpRequest.getMethod(), httpRequest.getRequestURI());
        
        try {
            chain.doFilter(request, response);
        } finally {
             //Clear MDC
            MDC.clear();
        }
    }
}
