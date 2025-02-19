/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.all;

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
import java.util.*;
import java.lang.*;
import model.CustomerRequest;
import model.Order;
import model.OrderItem;
import model.Table;

/**
 *
 * @author dung0
 */
@WebServlet(name = "GetTableNumber", urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetTableNumber</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetTableNumber at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        doPost(request, response);
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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        CustomerRequestDAO crd = new CustomerRequestDAO();
        if (action == null) {
            String tid_raw = (String) session.getAttribute("tid");
            int tid;
            TableDAO tD = new TableDAO();
            OrderDAO oD = new OrderDAO();
            try {
                tid = Integer.parseInt(tid_raw);
                Table t = tD.getTableById(tid);
                Order o = oD.getOrderInfo(tid, "unpaid");
                request.setAttribute("tableInfo", t);
                if (o == null) {
                    request.getRequestDispatcher("order.jsp").forward(request, response);
                }
                List<OrderItem> list_item = oD.getAllItemInOrderByDifferentStatus(o.getOrderId(), "unaccepted");
                double sum = 0;
                for (OrderItem item : list_item) {
                    double total = item.getQuantity() * item.getItemInMenu().getPrice();
                    sum += total;
                }

                Map<Integer, String> requestStatusMap = new HashMap<>();

                for (OrderItem item : list_item) {
                    CustomerRequest cr = crd.getRequestByOrderItemId(item.getOrderItemId()); 
                    if (cr != null) {
                        requestStatusMap.put(item.getOrderItemId(), cr.getStatus());
                    } else {
                        requestStatusMap.put(item.getOrderItemId(), "noRequest");
                    }
                }
                request.setAttribute("map", requestStatusMap);
                request.setAttribute("dataAccept", list_item);
                request.setAttribute("totalOrder", sum);
                request.setAttribute("orderInfo", o);
                request.getRequestDispatcher("order.jsp").forward(request, response);
            } catch (Exception e) {
            }
        } else {
            String orderItemId_raw = request.getParameter("orderItemId");
            int orderItemId = Integer.parseInt(orderItemId_raw);
            if (crd.getRequestByOrderItemId(orderItemId) == null)
                crd.createRequest(Integer.parseInt(orderItemId_raw));
            else
                crd.updateStatus(crd.getRequestByOrderItemId(orderItemId).getRequestId(), "received");
            response.sendRedirect("order?tid=" + session.getAttribute("tid"));
        }
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
