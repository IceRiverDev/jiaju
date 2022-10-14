package com.yang.furniture.web;

import com.yang.furniture.entity.Furniture;
import com.yang.furniture.entity.Page;
import com.yang.furniture.service.FurnitureService;
import com.yang.furniture.service.Impl.FurnitureServiceImpl;
import com.yang.furniture.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 刘洋
 * @date 2022/6/2  9:11 AM
 */
@WebServlet(urlPatterns = {"/customerFurnitureServlet"})
public class CustomerFurnitureServlet extends BasicServlet {
    private final FurnitureService furnitureService = new FurnitureServiceImpl();

    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int limit = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Furniture> furniturePage = furnitureService.page(pageNo, limit);

        furniturePage.setUrl("customerFurnitureServlet?action=page");
        request.setAttribute("page", furniturePage);
        request.getRequestDispatcher("/views/customer/index.jsp").forward(request, response);
    }

    public void pageByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String furnitureName = request.getParameter("name");
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int limit = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        if (furnitureName == null) {
            furnitureName = "";
        }
        Page<Furniture> furniturePage = furnitureService.pageByName(pageNo, limit, furnitureName);
        StringBuilder url = new StringBuilder("customerFurnitureServlet?action=pageByName");
        if (!"".equals(furnitureName)) {
            url.append("&name=").append(furnitureName);
        }

        request.setAttribute("page", furniturePage);
        furniturePage.setUrl(url.toString());
        System.out.println(furniturePage);
        request.getRequestDispatcher("/views/customer/index.jsp").forward(request, response);
    }
}
