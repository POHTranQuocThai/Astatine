/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Ma Tan Loc - CE181795
 */
public class adUserServelt extends HttpServlet {

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

        UserDAO uDAO = new UserDAO();

        String action = request.getParameter("action");
        String idParam = request.getParameter("id");
        String indexPage = request.getParameter("index");

        int index = (indexPage == null || indexPage.isEmpty()) ? 1 : Integer.parseInt(indexPage);
        request.setAttribute("index", index);

        if (action == null) {
            action = "list";
        }
        if ("list".equals(action)) {
            int count = uDAO.getNumberPage();
            int endPage = count / 3;
            if (count % 3 != 0) {
                endPage++;
            }

            int maxPagesToShow = 6;
            int startPage, endPageLimit;

            if (index <= maxPagesToShow / 2) {
                startPage = 1;
                endPageLimit = Math.min(maxPagesToShow, endPage);
            } else if (index > endPage - maxPagesToShow / 2) {
                startPage = Math.max(1, endPage - maxPagesToShow + 1);
                endPageLimit = endPage;
            } else {
                startPage = index - maxPagesToShow / 2;
                endPageLimit = startPage + maxPagesToShow - 1;
            }
            
            List<User> list = null;
            try {
                list = uDAO.getPagingAd(index);
            } catch (SQLException ex) {
                Logger.getLogger(adUserServelt.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("listPr", list);
            request.setAttribute("endP", endPage);
            request.setAttribute("startPage", startPage);
            request.setAttribute("endPageLimit", endPageLimit);
            request.setAttribute("currentPage", index);
            
            request.getRequestDispatcher("/WEB-INF/adListUser.jsp").forward(request, response);
        }

        if (idParam == null || idParam.isEmpty()) {
            if ("edit".equals(action)) {
                request.getRequestDispatcher("/WEB-INF/adEditUser.jsp").forward(request, response);
            }
            if ("delete".equals(action)) {
                request.getRequestDispatcher("/WEB-INF/adListUser.jsp").forward(request, response);
            }
        } else {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(idParam);
                User user = uDAO.getUserById(id);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/adEditUser.jsp").forward(request, response);
            }
            if ("delete".equals(action)) {
                int id = Integer.parseInt(idParam);
                User user = uDAO.getUserById(id);
                request.setAttribute("user", user);
                System.out.println(user);
                request.getRequestDispatcher("/WEB-INF/adDeleteUser.jsp").forward(request, response);
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

        UserDAO uDAO = new UserDAO();

        String action = request.getParameter("action");
        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);

        if (action.equals("edit")) {

            int userId = id;
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            boolean isAdmin = request.getParameter("isAdmin") != null;
            String street = request.getParameter("street");
            String ward = request.getParameter("ward");
            String district = request.getParameter("district");
            String city = request.getParameter("city");
            String country = request.getParameter("country");

            User user = new User(userId, fullname, email, password, phone, street, ward, district, city, country, isAdmin);

            try {
                uDAO.updateUser(user);
            } catch (SQLException ex) {
                Logger.getLogger(adUserServelt.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("user", user);
            response.sendRedirect("User?action=list");

        }

        if (action.equals("delete")) {
            uDAO.deleteUser(id);
            response.sendRedirect("User?action=list");
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
