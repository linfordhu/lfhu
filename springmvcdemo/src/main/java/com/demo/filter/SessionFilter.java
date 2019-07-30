package com.demo.filter;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * spring mvc filter test
 * @author hlf
 * @since 2019/07/22 09:01
 */
public class SessionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String[] notFilter = new String[]{"/login"};
        String uri = httpServletRequest.getRequestURI();
        System.out.println("filter>>>uri>>>"+uri);
        boolean doFilter = true;
        for(String s: notFilter) {
            if(uri.contains(s)) {
                doFilter = false;
                break;
            }
        }

        if(doFilter) {
            System.out.println("doFilter>>>");
            Object obj = httpServletRequest.getSession().getAttribute("user");
            if ( obj == null ) {
                System.out.println("doFilter>>>obj is null");
                boolean isAjaxRequest = isAjaxRequest(httpServletRequest);
                if (isAjaxRequest) {
                    httpServletResponse.setCharacterEncoding("UTF-8");
                    httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作，请刷新页面");
                    System.out.println("doFilter>>>ajax request");
                } else {
                    System.out.println("doFilter>>>http request");
                    httpServletResponse.sendRedirect("./login");
                }
            } else {
                System.out.println("no Filter>>>");
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        }
    }
    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(header);
    }
}
