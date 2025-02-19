/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CustomerRequestDAO;
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
import model.Order;
import java.util.*;
import model.OrderItem;
import model.Table;

/**
 *
 * @author lenha
 */
@WebServlet(name = "OrderDashboardController", urlPatterns = {"/orderdashboard"})
public class OrderDashboardController extends HttpServlet {

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
        String orderId_raw = request.getParameter("oid");
        String action_raw = request.getParameter("action");
        String orderItemId_raw = request.getParameter("item");
        HttpSession session = request.getSession();
        if (orderId_raw == null && action_raw == null) {
            OrderDAO oD = new OrderDAO();
            TableDAO tD = new TableDAO();
            List<Order> list_order = oD.getAllOrder();
            List<Table> list_Table = tD.getAll();//lay toan bo table
            session.setAttribute("listTable", list_Table);// dung session de luu len server
            request.setAttribute("dataOrder", list_order);
            request.getRequestDispatcher("order_dashboard.jsp").forward(request, response);
        } else if (orderId_raw != null && action_raw == null) {
            try {
                OrderDAO oD = new OrderDAO();
                int orderId = Integer.parseInt(orderId_raw);
                List<OrderItem> list1 = oD.getAllItemInOrderByDifferentStatus(orderId, "unaccepted");
                request.setAttribute("detailOrder", list1);
                request.getRequestDispatcher("Detail-Order.jsp").forward(request, response);
            } catch (Exception e) {
            }
        } else if (action_raw != null) {
            try {
                OrderDAO oD = new OrderDAO();
                int action = Integer.parseInt(action_raw);
                int item = Integer.parseInt(orderItemId_raw);
                OrderItem oi = new OrderItem();
                if (action == 1) {
                    oD.updateItemStatus("preparing", item);
                    response.sendRedirect("orderdashboard?oid=" + oD.getOrderItemById(item).getOrderId().getOrderId());
                } else if (action == 2) {
                    oi = oD.getOrderItemInOrderByStatus(oD.getOrderItemById(item).getItemInMenu().getId(), oD.getOrderItemById(item).getOrderId(), "served");
                    if (oi == null) {
                        oD.updateItemStatus("served", item);
                        response.sendRedirect("orderdashboard?oid=" + oD.getOrderItemById(item).getOrderId().getOrderId());
                    }
                    else{
                        oD.updateQuantity(oD.getOrderItemById(item).getQuantity() + oi.getQuantity(), oi);
                        CustomerRequestDAO crd = new CustomerRequestDAO();
                        crd.deleteRequest(item);
                        oD.deleteOrderItemBYStatus("preparing", oD.getOrderItemById(item));
                        response.sendRedirect("orderdashboard?oid=" + oi.getOrderId().getOrderId());
                    }
                }
            } catch (Exception e) {
            }
        }
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
