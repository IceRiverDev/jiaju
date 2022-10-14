package com.yang.furniture.web.filters;

import com.yang.furniture.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;


/**
 * @author 刘洋
 * @date 2022/6/8  10:36 PM
 */

@WebFilter(filterName = "TransactionFilter")
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JDBCUtilsByDruid.commit();
        } catch (Exception e) {
            JDBCUtilsByDruid.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
