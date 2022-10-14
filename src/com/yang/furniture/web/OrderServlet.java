package com.yang.furniture.web;

import com.yang.furniture.entity.Cart;
import com.yang.furniture.entity.Member;
import com.yang.furniture.entity.Order;
import com.yang.furniture.entity.OrderItem;
import com.yang.furniture.service.Impl.OrderServiceImpl;
import com.yang.furniture.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 刘洋
 * @date 2022/6/5  2:53 PM
 */

@WebServlet(urlPatterns = {"/orderServlet"})
public class OrderServlet extends BasicServlet {

    private final OrderService orderService = new OrderServiceImpl();

    public void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object member = request.getSession().getAttribute("member");
        if (member != null) {
            Integer memberId = ((Member) member).getId();
            List<Order> orders = orderService.listOrderByMemberId(memberId);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/views/order/order.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
        }
    }

    public void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("id");
        if (orderId != null) {
            List<OrderItem> orderItems = orderService.listOrderItemByOrderId(orderId);
            BigDecimal totalPrice = orderService.getTotalPrice(orderId);
            int totalOrderItemCount = orderService.getTotalItemCount(orderId);
            request.setAttribute("orderItems", orderItems);
            request.setAttribute("orderItemTotalPrice", totalPrice);
            request.setAttribute("totalOrderItemCount", totalOrderItemCount);
        }

        request.getRequestDispatcher("/views/order/order_detail.jsp").forward(request, response);
    }

    public void saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        Member member = (Member) request.getSession().getAttribute("member");
        if (member == null) {
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            return;
        }

        Order order = orderService.saveOrder(cart, member.getId());
        request.getSession().setAttribute("orderId", order.getId());
        response.sendRedirect(request.getContextPath() + "/views/order/checkout.jsp");
    }
}
