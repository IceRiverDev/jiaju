package com.yang.furniture.web;

import com.yang.furniture.entity.Member;
import com.yang.furniture.service.Impl.MemberServiceImpl;
import com.yang.furniture.service.MemberService;
import com.yang.furniture.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 刘洋
 * @date 2022/5/25  10:48 PM
 */
@WebServlet(urlPatterns = {"/adminServlet"})
@Deprecated
public class AdminServlet extends BasicServlet {
    private final MemberService memberService = new MemberServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = new Member();
        DataUtils.copyParamToBean(request.getParameterMap(), member);
        member = memberService.login(member);
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("member", member);
            request.getRequestDispatcher("/views/manage/manage_menu.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/manage/manage_login.jsp").forward(request, response);
        }
    }
}
