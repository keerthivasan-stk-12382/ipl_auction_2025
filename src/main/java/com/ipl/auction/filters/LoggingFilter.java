package com.ipl.auction.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger("WebAccessLogger");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Start time to measure duration
        long startTime = System.currentTimeMillis();

        try {
            // Set MDC (Mapped Diagnostic Context) values for logging
            MDC.put("httpMethod", httpRequest.getMethod());
            MDC.put("requestURI", httpRequest.getRequestURI());
            MDC.put("queryString", httpRequest.getQueryString());

            // Proceed with the request
            chain.doFilter(request, response);

            // Set response status
            MDC.put("statusCode", String.valueOf(httpResponse.getStatus()));

        } finally {
            // Calculate and log the duration
            long duration = System.currentTimeMillis() - startTime;
            MDC.put("duration", String.valueOf(duration));

            // Log the access details
            logger.info("Access log entry");

            // Clear MDC to prevent memory leaks
            MDC.clear();
        }
    }

}
