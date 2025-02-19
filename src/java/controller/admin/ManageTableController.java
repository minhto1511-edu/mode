/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.BillDAO;
import dal.CustomerRequestDAO;
import dal.OrderDAO;
import dal.TableDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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
public class ManageTableController extends HttpServlet {

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
            out.println("<title>Servlet manageTable</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet manageTable at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String table_st = request.getParameter("action");
        String table_number = request.getParameter("tid");
        if (table_st != null && table_number != null) {
            int tid;
            TableDAO tD = new TableDAO();
            OrderDAO oD = new OrderDAO();
            try {
                tid = Integer.parseInt(table_number);
                Order temp = oD.getOrderInfo(tid, "unpaid");
                if (temp != null) {
                    tD.updateStatusTable(temp.getTableId(), "occupied");
                }
                if (table_st.equals("order")) {
                    oD.createOrder(tid);
                    tD.updateStatusTable(tid, "occupied");
                    Order o = oD.getOrderInfo(tid, "unpaid");
                } else if (table_st.equals("finish")) {
                    Order o = oD.getOrderInfo(tid, "unpaid");
                    if (o != null) {
                        oD.deleteItemByOidAndStatus(o.getOrderId(), "unaccepted");
                        List<OrderItem> check = oD.getAllItemInOrder(o.getOrderId());
                        if (check==null) {
                            oD.deleteOrderById(o.getOrderId());
                        } else {
                            OrderItem item = oD.getOrderItemNotServed(o);
                            if (item != null) {
                                request.setAttribute("err", "Vẫn còn món chưa ra cho khách!");
                            } else {
                                List<OrderItem> list_item = oD.getAllItemInOrderByItemStatus(o.getOrderId(), "served");
                                double sum = 0;
                                for (OrderItem oi : list_item) {
                                    double total = oi.getQuantity() * oi.getItemInMenu().getPrice();
                                    sum += total;
                                }
                                BillDAO bD = new BillDAO();
                                bD.createBill(o.getOrderId(), sum);
                                oD.updateOrder(o, sum);
                                CustomerRequestDAO crd = new CustomerRequestDAO();
                                for (OrderItem orderItem : list_item) {
                                    crd.deleteRequest(orderItem.getOrderItemId());
                                }
                                tD.updateStatusTable(tid, "available");
                                session.removeAttribute("tid");
                            }
                        }
                    }
                }
            List<Table> list = tD.getAll();
            request.setAttribute("dataTable", list);
            request.getRequestDispatcher("manageTable.jsp").forward(request, response);
            } catch (Exception e) {
            }
        } else if (table_st == null && table_number == null) {
            TableDAO tD = new TableDAO();
            List<Table> list = tD.getAll();
            request.setAttribute("dataTable", list);
            request.getRequestDispatcher("manageTable.jsp").forward(request, response);
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
