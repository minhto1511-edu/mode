/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.TableDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import java.lang.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Table;
import model.User;

/**
 *
 * @author dung0
 */
@WebServlet(name = "createTable", urlPatterns = {"/createtable"})
public class CreateTableController extends HttpServlet {

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
        String action = request.getParameter("action");
        TableDAO tD = new TableDAO();
        if(action.equals("1")){
            if(request.getMethod().equalsIgnoreCase("GET")){
                request.getRequestDispatcher("createTable.jsp").forward(request, response);
            } else if (request.getMethod().equalsIgnoreCase("POST")){
                String tnum_raw = request.getParameter("tnum");
                String capacity_raw = request.getParameter("cnum");
                int tnum, cnum;
                try {
                    tnum = Integer.parseInt(tnum_raw);
                    cnum = Integer.parseInt(capacity_raw);
                    Table t = tD.getTableByTableNumber(tnum);
                    if (t==null){
                        tD.insertNewTable(tnum, cnum);
                        response.sendRedirect("managetable");
                    } else {
                        request.setAttribute("err", "Bàn đã tồn tại!");
                        request.getRequestDispatcher("createTable.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                }
            }
        } else if(action.equals("2")){
            if(request.getMethod().equalsIgnoreCase("GET")){
                String tid_raw = request.getParameter("tid");
                int tid;
                try {
                    tid = Integer.parseInt(tid_raw);
                    Table t = tD.getTableById(tid);
                    request.setAttribute("tableInfo", t);
                    request.getRequestDispatcher("EditTable.jsp").forward(request, response);
                } catch (Exception e) {
                }
            }else if (request.getMethod().equalsIgnoreCase("POST")){
                String tnum_raw = request.getParameter("tnum");
                String capacity_raw = request.getParameter("cnum");
                int tnum, cnum;
                try {
                    tnum = Integer.parseInt(tnum_raw);                    
                    cnum = Integer.parseInt(capacity_raw);
                    Table t = tD.getTableByTableNumber(tnum);
                    tD.updateTable(t.getTableId(), tnum, cnum, "available");
                    response.sendRedirect("managetable");
                } catch (Exception e) {
                }
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
