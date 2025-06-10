package com.mapnet.filter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter implements Filter {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String ip = request.getRemoteAddr();
        Bucket bucket = buckets.computeIfAbsent(ip, this::createNewBucket);

        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setStatus(429); // Código HTTP 429 (Too Many Requests)
            resp.getWriter().write("Muitas requisições! Tente novamente mais tarde.");
        }
    }

    private Bucket createNewBucket(String ip) {
        Refill refill = Refill.greedy(10, Duration.ofMinutes(1)); // 10 req/min por IP
        Bandwidth limit = Bandwidth.classic(10, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }
}
