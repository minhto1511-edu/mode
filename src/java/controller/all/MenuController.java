/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.all;

import dal.CategoryDAO;
import dal.MenuItemDAO;
import dal.OrderDAO;
import dal.TableDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.Table;

/**
 *
 * @author lenha
 */
@WebServlet(name = "MenuController", urlPatterns = {"/menu"})
public class MenuController extends HttpServlet {

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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action == null) {
            TableDAO td = new TableDAO();
            Table t = td.getTableById(Integer.parseInt((String)session.getAttribute("tid")));
            request.setAttribute("table", t);
            CategoryDAO cD = new CategoryDAO();
            List<Category> listC = cD.getAllCategory();
            request.setAttribute("dataC", listC);
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
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("addToCart")) {
            String tid_raw = (String) session.getAttribute("tid");
            String item_id_raw = request.getParameter("item");
            int tid, item_id;
            OrderDAO oD = new OrderDAO();

            try {
                tid = Integer.parseInt(tid_raw);
                item_id = Integer.parseInt(item_id_raw);
                Order o = oD.getOrderInfo(tid, "unpaid");
                OrderItem oi = oD.checkOrderItem(item_id, o);
                if (oi == null) {
                    oD.insertOrderItem(o.getOrderId(), item_id);
                } else if (oi != null) {
                    if (oi.getItem_status().equals("preparing") || oi.getItem_status().equals("served") || oi.getItem_status().equals("pending")) {
                        oD.insertOrderItem(o.getOrderId(), item_id);
                    } else {
                        oD.updateOrderItem(oi, 2);
                    }

                }
                response.sendRedirect("menu?cid=0");
            } catch (Exception e) {
            }
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
