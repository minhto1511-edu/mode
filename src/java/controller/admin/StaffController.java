/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.User;

/**
 *
 * @author lenha
 */
@WebServlet(name = "StaffController", urlPatterns = {"/staff"})
public class StaffController extends HttpServlet {

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
        UserDAO uD = new UserDAO();
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                if (request.getMethod().equalsIgnoreCase("GET")) {
                    request.getRequestDispatcher("createUser.jsp").forward(request, response);
                } else if (request.getMethod().equalsIgnoreCase("POST")) {
                    // Xử lý thêm user
                    String user_raw = request.getParameter("user");
                    String pass_raw = request.getParameter("pass");
                    String fullname_raw = request.getParameter("fullname");
                    String role_raw = request.getParameter("role");
                    String email_raw = request.getParameter("email");
                    String phone_raw = request.getParameter("phone");
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    User u = uD.getUserByUsername(user_raw);
                    if (u == null) {
                        User newU = new User(0, user_raw, pass_raw, role_raw, fullname_raw, email_raw, phone_raw, LocalDateTime.now().format(df));
                        uD.insertUser(newU);
                        response.sendRedirect("staff");
                    } else {
                        request.setAttribute("error", "Username đã tồn tại");
                        request.getRequestDispatcher("createUser.jsp").forward(request, response);
                    }
                }
                break;
            case "edit":
                if (request.getMethod().equalsIgnoreCase("GET")) {
                    // Lấy thông tin user để chỉnh sửa
                    String username = request.getParameter("user");
                    User u = uD.getUserByUsername(username);
                    request.setAttribute("userEdit", u);
                    request.getRequestDispatcher("editUser.jsp").forward(request, response);
                } else if (request.getMethod().equalsIgnoreCase("POST")) {
                    // Xử lý cập nhật thông tin user
                    String user_raw = request.getParameter("user");
                    String pass_raw = request.getParameter("pass");
                    String fullname_raw = request.getParameter("fullname");
                    String role_raw = request.getParameter("role");
                    String email_raw = request.getParameter("email");
                    String phone_raw = request.getParameter("phone");
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    User updatedUser = new User(0, user_raw, pass_raw, role_raw, fullname_raw, email_raw, phone_raw, LocalDateTime.now().format(df));
                    uD.updateUser(updatedUser);
                    response.sendRedirect("staff");
                }
                break;
            case "delete":
                // Xử lý xóa user
                String username = request.getParameter("user");
                User u = uD.getUserByUsername(username);
                if (u != null) {
                    uD.deleteUserByUsername(u.getUsername());
                }
                response.sendRedirect("staff");
                break;
            case "list":
            default:
                // Hiển thị danh sách user
                List<User> list = uD.getAll();
                request.setAttribute("dataUser", list);
                request.getRequestDispatcher("staff.jsp").forward(request, response);
                break;
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
