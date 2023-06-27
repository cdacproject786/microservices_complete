package com.customer.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadSheddingFilter implements Filter {

    private final int maxRequests = 150;

    private AtomicInteger currentRequest = new AtomicInteger(0);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if(currentRequest.get() >= maxRequests)
        {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            byte[] byteMessage = "Server unavailable. Please try after some time..".getBytes(StandardCharsets.UTF_8);
            response.getOutputStream().write(byteMessage);
            return;
        }
        currentRequest.incrementAndGet();

      try{
          filterChain.doFilter(servletRequest,servletResponse);
      }
      finally {
          currentRequest.decrementAndGet();
      }

    }
}
