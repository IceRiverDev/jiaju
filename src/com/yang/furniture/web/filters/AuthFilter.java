package com.yang.furniture.web.filters;

import com.google.gson.Gson;
import com.yang.furniture.entity.Member;
import com.yang.furniture.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 刘洋
 * @date 2022/6/8  9:19 AM
 */

@WebFilter(filterName = "AuthFilter", initParams = {@WebInitParam(name = "excludedUrls", value = "/views/manage/manage_login.jsp, /views/member/login.jsp")})
public class AuthFilter implements Filter {
    private List<String> excludedUrls = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String strExcludedUrls = filterConfig.getInitParameter("excludedUrls");
        String[] urls = strExcludedUrls.split("\\s*,\\s*");

        excludedUrls = Arrays.stream(urls).map(String::trim).collect(Collectors.toList());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        Object member = httpServletRequest.getSession().getAttribute("member");
        if (!excludedUrls.contains(httpServletRequest.getServletPath())) {
            if (member == null) {
                if (!WebUtils.isAjaxRequest(httpServletRequest)) {
                    httpServletRequest.getRequestDispatcher("/views/member/login.jsp").forward(servletRequest, servletResponse);
                } else {
                    HashMap<String, Object> resultMap = new HashMap<>();
                    resultMap.put("url", "views/member/login.jsp");
                    String resultJson = new Gson().toJson(resultMap);
                    servletResponse.getWriter().write(resultJson);
                }
            } else {
                if (((Member) member).getRole() != 1) {
                    String servletPath = httpServletRequest.getServletPath();
                    if (servletPath.contains("/views/manage") || servletPath.contains("/furnitureServlet")) {
                        ((HttpServletResponse) httpServletRequest).sendError(404);
                        return;
                    }
                }
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
