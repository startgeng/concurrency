package com.kevin.concurrentcy.filter;

import com.kevin.concurrentcy.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-05-04 1:17
 */
@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        log.info("add Filter,{},{}",Thread.currentThread().getId(),request.getServletPath());
        RequestHolder.add((int) Thread.currentThread().getId());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
