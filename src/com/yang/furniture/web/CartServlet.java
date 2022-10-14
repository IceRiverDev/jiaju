package com.yang.furniture.web;

import com.google.gson.Gson;
import com.yang.furniture.entity.Cart;
import com.yang.furniture.entity.CartItem;
import com.yang.furniture.entity.Furniture;
import com.yang.furniture.service.FurnitureService;
import com.yang.furniture.service.Impl.FurnitureServiceImpl;
import com.yang.furniture.utils.DataUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author 刘洋
 * @date 2022/6/4  9:28 AM
 */
@WebServlet(urlPatterns = {"/cartServlet"})
public class CartServlet extends BasicServlet {
    private final FurnitureService furnitureService = new FurnitureServiceImpl();

    @Deprecated
    public void addItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = DataUtils.parseInt(request.getParameter("id"), 0);

        Furniture furniture = furnitureService.getFurnitureById(id);

        CartItem cartItem = new CartItem(furniture.getId(), furniture.getPrice(), 1, furniture.getName(), furniture.getPrice(), furniture.getImagePath());
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            cart.addItem(cartItem);
            request.getSession().setAttribute("cart", cart);
        } else {
            cart.addItem(cartItem);
        }

        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
    }

    public void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = DataUtils.parseInt(request.getParameter("id"), 0);

        Furniture furniture = furnitureService.getFurnitureById(id);

        CartItem cartItem = new CartItem(furniture.getId(), furniture.getPrice(), 1, furniture.getName(), furniture.getPrice(), furniture.getImagePath());
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            cart.addItem(cartItem);
            request.getSession().setAttribute("cart", cart);
        } else {
            cart.addItem(cartItem);
        }

        HashMap<String, Object> bodyHashMap = new HashMap<>();
        bodyHashMap.put("cartItemsTotalCount", cart.getTotalCount());
        String jsonData = new Gson().toJson(bodyHashMap);
        response.getWriter().write(jsonData);
    }

    public void deleteItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Object cart = request.getSession().getAttribute("cart");

        if (cart != null) {
            ((Cart) cart).deleteItem(DataUtils.parseInt(id, 0));
        }

        response.sendRedirect(request.getHeader("referer"));
    }

    public void updateCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String count = request.getParameter("count");
        String id = request.getParameter("id");
        Object cart = request.getSession().getAttribute("cart");

        if (cart != null) {
            ((Cart) cart).updateCount(DataUtils.parseInt(id, 0), DataUtils.parseInt(count, 0));
        }

        response.sendRedirect(request.getHeader("referer"));
    }

    public void clear(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object cart = request.getSession().getAttribute("cart");
        if (cart != null) {
            ((Cart) cart).clear();
        }

        response.sendRedirect(request.getHeader("referer"));
    }
}
