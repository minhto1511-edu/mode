/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CategoryDAO;
import dal.MenuItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.MenuItem;

/**
 *
 * @author lenha
 */
@WebServlet(name = "MenuDashboardController", urlPatterns = {"/menudashboard"})
public class MenuDashboardController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //sửa
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categoryList = categoryDAO.getAllCategory();
        request.setAttribute("categoryList", categoryList);

        MenuItemDAO mD = new MenuItemDAO();
        List<MenuItem> list;
        String cid_raw = request.getParameter("cid");
        int cid;
        try {
            cid = Integer.parseInt(cid_raw);
            if (cid == 0) {
                list = mD.getAllMenuItem();
            } else {
                list = mD.getAllByCid(cid);
            }
            request.setAttribute("dataM", list);
            request.setAttribute("cid", cid);// thêm trả về cid để update hiệu ứng chọn category
        } catch (Exception e) {
            System.out.println(e);
        }
        
        String action = request.getParameter("action");
        if (action == null) {
            if ("POST".equalsIgnoreCase(request.getMethod())) {
            String foodName = request.getParameter("food-name");
            String foodDetail = request.getParameter("food-detail");
            int foodPrice = Integer.parseInt(request.getParameter("food-price"));
            String foodImage = request.getParameter("food-image");
            int categoryId = Integer.parseInt(request.getParameter("food-category"));

            MenuItem food = new MenuItem(0, foodName, foodDetail, foodPrice, categoryId, foodImage);

            MenuItemDAO menuItemDAO = new MenuItemDAO();
            if (menuItemDAO.getMenuItemByName(foodName) != null) {
                String message = "Món đã tồn tại. Thêm món thất bại";
                request.setAttribute("message", message);
                request.setAttribute("mode", 0);
            } else {
                menuItemDAO.createMenuItem(food);
                String message = "Thêm món thành công";
                request.setAttribute("message", message);
                request.setAttribute("mode", 1);
            }
        }
            request.getRequestDispatcher("menu_dashboard.jsp").forward(request, response);
        } 
        else if (action.equalsIgnoreCase("edit")) {
            if (request.getMethod().equalsIgnoreCase("get")) {
                String id = request.getParameter("item");
                MenuItem menuItem = mD.getMenuItemById(Integer.parseInt(id));
                request.setAttribute("item", menuItem);
                request.getRequestDispatcher("editItem.jsp").forward(request, response);
            } 
            else if (request.getMethod().equalsIgnoreCase("post")) {
                int id = Integer.parseInt(request.getParameter("item"));
                String itemName = request.getParameter("itemName");
                String itemDescription = request.getParameter("itemDescription");
                int price = (int) Double.parseDouble(request.getParameter("price"));
                int categoryId = Integer.parseInt(request.getParameter("category"));
                String imageUrl = request.getParameter("image");

                MenuItem updatedItem = new MenuItem(id, itemName, itemDescription, price, categoryId, imageUrl);
                mD.updateMenuItem(updatedItem);

                // Sau khi cập nhật xong, chuyển hướng về trang dashboard
                response.sendRedirect("menudashboard?cid=0");
            }
        }
        else if (action.equalsIgnoreCase("createCate")) {
            if(request.getMethod().equalsIgnoreCase("get")) {
                request.getRequestDispatcher("createCategory.jsp").forward(request, response);
            }
            else if(request.getMethod().equalsIgnoreCase("post")) {
                String cate_raw = request.getParameter("cate");
                CategoryDAO cd = new CategoryDAO();
                if(cd.getCategoryByName(cate_raw) != null) {
                    request.setAttribute("error", "Danh mục món đã tồn tại!");
                    request.getRequestDispatcher("createCategory.jsp").forward(request, response);
                }
                else{
                    cd.createCategory(cate_raw);
                    response.sendRedirect("menudashboard?cid=0");
                }
            }
        }
        else if (action.equalsIgnoreCase("deleteCate")) {
            if(request.getMethod().equalsIgnoreCase("get")) {
                CategoryDAO cd = new CategoryDAO();
                List<Category> listC = cd.getAllCategory();
                request.setAttribute("list", listC);
                request.getRequestDispatcher("deleteCategory.jsp").forward(request, response);
            }
            else if(request.getMethod().equalsIgnoreCase("post")) {
                String cate_raw = request.getParameter("cate");
                CategoryDAO cd = new CategoryDAO();
                MenuItemDAO md = new MenuItemDAO();
                md.deleteMenuItemByCategoryId(Integer.parseInt(cate_raw));
                cd.deleteCategory(Integer.parseInt(cate_raw));
                response.sendRedirect("menudashboard?cid=0");
            }
        }
        else if (action.equalsIgnoreCase("delete")) {
            String itemId = request.getParameter("item");
            int id = Integer.parseInt(itemId);
            mD.deleteMenuItem(id);
            response.sendRedirect("menudashboard?cid=0");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
