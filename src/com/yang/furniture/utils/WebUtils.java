package com.yang.furniture.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘洋
 * @date 2022/6/10  9:25 AM
 */
public class WebUtils {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        //XMLHttpRequest
        String requestHeader = request.getHeader("X-Requested-With");
        if (requestHeader != null) {
            return requestHeader.equalsIgnoreCase("XMLHttpRequest");
        }
        return false;
    }
}
