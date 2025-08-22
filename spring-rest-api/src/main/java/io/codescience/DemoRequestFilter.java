package io.codescience;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class DemoRequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 在 request 中设置属性
        request.setAttribute("userId", "12345");
        request.setAttribute("userRole", "Admin");

        chain.doFilter(request, response);
    }
}
