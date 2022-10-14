package com.yang.furniture.web;

import com.google.code.kaptcha.Constants;

import com.google.gson.Gson;
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
import java.util.HashMap;

/**
 * @author 刘洋
 * @date 2022/5/24  8:20 PM
 */
@WebServlet(urlPatterns = {"/memberServlet"})
public class MemberServlet extends BasicServlet {
    private final MemberService memberService = new MemberServiceImpl();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member = new Member();
        DataUtils.copyParamToBean(req.getParameterMap(), member);
        member = memberService.login(member);
        if (member != null) {
            HttpSession session = req.getSession();
            session.setAttribute("member", member);
            if ("admin".equals(member.getUsername())) {
                req.getRequestDispatcher("/views/manage/manage_menu.jsp")
                        .forward(req, resp);
            } else {
                req.getRequestDispatcher("/views/member/login_ok.jsp").forward(req, resp);
            }

        } else {
            req.setAttribute("msg", "用户名或密码不正确!");
            req.setAttribute("username", req.getParameter("username"));
            req.getRequestDispatcher("/views/member/login.jsp").forward(req, resp);
        }
    }

    public void isMemberExist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        boolean exists = memberService.checkUserIfExists(username);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("isExists", exists);
        String json = new Gson().toJson(hashMap);
        response.getWriter().write(json);
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member = new Member();
        DataUtils.copyParamToBean(req.getParameterMap(), member);
        boolean exists = memberService.checkUserIfExists(member.getUsername());
        if (exists) {
            req.setAttribute("error_msg", "用户已经存在!");
            req.setAttribute("username", member.getUsername());
            req.getRequestDispatcher("/views/member/login.jsp").forward(req, resp);
            return;
        }

        String code = req.getParameter("code");
        String codeImage = ((String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));
        req.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);

        if (codeImage != null && code.equalsIgnoreCase(codeImage)) {
            boolean registerMember = memberService.registerMember(member);
            if (registerMember) {
                req.getSession().setAttribute("member", member);
                req.getRequestDispatcher("/views/member/register_ok.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/views/member/register_fail.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码不正确!");
            req.setAttribute("active", "register");
            req.setAttribute("username", member.getUsername());
            req.setAttribute("email", member.getEmail());
            req.getRequestDispatcher("/views/member/login.jsp").forward(req, resp);
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        response.sendRedirect(getServletContext().getContextPath());
    }

    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, Object> dataHashMap = new HashMap<>();
        String code = request.getParameter("code");
        String codeImage = ((String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));
        if (codeImage != null && code.equalsIgnoreCase(codeImage)) {
            dataHashMap.put("isCodeCorrect", true);
        } else {
            dataHashMap.put("isCodeCorrect", false);
        }

        String jsonData = new Gson().toJson(dataHashMap);
        response.getWriter().write(jsonData);
    }
}
