package com.yang.furniture.web;

import com.yang.furniture.entity.Furniture;
import com.yang.furniture.entity.Page;
import com.yang.furniture.service.FurnitureService;
import com.yang.furniture.service.Impl.FurnitureServiceImpl;
import com.yang.furniture.utils.DataUtils;
import com.yang.furniture.utils.FileUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.yang.furniture.constants.Constants.*;
import static com.yang.furniture.constants.Constants.UPLOAD_DIRECTORY;

/**
 * @author 刘洋
 * @date 2022/5/25  9:53 PM
 */
@WebServlet(urlPatterns = {"/furnitureServlet"})
public class FurnitureServlet extends BasicServlet {
    private final FurnitureService furnitureService = new FurnitureServiceImpl();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Furniture> allFurniture = furnitureService.list();
        request.setAttribute("furns", allFurniture);
        request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Furniture furniture = new Furniture();
        DataUtils.copyParamToBean(request.getParameterMap(), furniture);
        furnitureService.addFurniture(furniture);
        response.sendRedirect(request.getContextPath() + "/furnitureServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    public void showFurniture(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String pageNo = request.getParameter("pageNo");

        request.setAttribute("pageNo", pageNo);
        Furniture furniture = furnitureService.showFurniture(DataUtils.parseInt(id, 0));
        request.setAttribute("furn", furniture);
        request.getRequestDispatcher("/views/manage/furn_update.jsp").forward(request, response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 0);

        furnitureService.deleteFurniture(id);
        Page<Furniture> page = furnitureService.page(pageNo, Page.PAGE_SIZE);
        if (page.getItems().size() == 0) {
            pageNo = pageNo - 1;
        }
        response.sendRedirect(request.getContextPath() + "/furnitureServlet?action=page&pageNo=" + pageNo);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer id = DataUtils.parseInt(request.getParameter("id"), 0);
        Furniture furniture = furnitureService.getFurnitureById(id);

        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator +
                    FileUtils.generateUploadPath();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            try {
                List<FileItem> formItems = upload.parseRequest(request);

                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            String fileName = new File(item.getName()).getName();
                            if (!fileName.equalsIgnoreCase("")) {
                                String filePath = uploadPath + File.separator + fileName;
                                File storeFile = new File(filePath);
                                String oldImageName = furniture.getImagePath();
                                try {
                                    Files.delete(Path.of(getServletContext().getRealPath("") + File.separator + oldImageName));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                furniture.setImagePath(UPLOAD_DIRECTORY + File.separator + FileUtils.generateUploadPath() + File.separator + fileName);
                                item.write(storeFile);
                                item.getOutputStream().close();
                            }
                        } else {
                            switch (item.getFieldName()) {
                                case "name" -> {
                                    furniture.setName(item.getString("utf-8"));
                                }
                                case "maker" -> {
                                    furniture.setMaker(item.getString("utf-8"));
                                }
                                case "sales" -> {
                                    furniture.setSales(DataUtils.parseInt(item.getString(), furniture.getSales()));
                                }
                                case "stock" -> {
                                    furniture.setStock(DataUtils.parseInt(item.getString(), furniture.getStock()));
                                }
                                case "price" -> {
                                    furniture.setPrice(new BigDecimal(item.getString()));
                                }
                                default -> {
                                    System.out.println("未知字段名: " + item.getFieldName());
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println(furniture);
            //String pageNo = request.getParameter("pageNo");
            furnitureService.updateFurniture(furniture);
            //response.sendRedirect(request.getContextPath() + "/furnitureServlet?action=page&pageNo=" + pageNo);

            request.getRequestDispatcher("/views/manage/update_ok.jsp").forward(request, response);
        }
    }

    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int limit = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Furniture> furniturePage = furnitureService.page(pageNo, limit);
        request.setAttribute("page", furniturePage);
        request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);
    }
}
