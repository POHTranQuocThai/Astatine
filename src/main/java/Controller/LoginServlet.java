/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class LoginServlet extends HttpServlet {

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
        request.getRequestDispatcher("/login.jsp").forward(request, response);
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

        try {
            String email = request.getParameter("email");
            String pass = request.getParameter("password");

            UserDAO uDAO = new UserDAO();

            // Kiểm tra đăng nhập qua UserDAO
            if (!uDAO.login(email, uDAO.getHashPass(pass))) {
                // Nếu đăng nhập thất bại, trả về trang đăng nhập với thông báo lỗi
                request.setAttribute("mess", "Email or password invalid!");
                request.setAttribute("email", email);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                // Nếu đăng nhập thành công, lưu thông tin người dùng vào session
            } else {
                User user = new User(uDAO.getUserId(email), pass, email);
                user.setIsAdmin(uDAO.checkIsAdmin(email));
                HttpSession session = request.getSession();
                user = uDAO.getUserByEmail(email);
                session.setAttribute("email", user.getEmail());              
                session.setAttribute("User", user);
                session.setAttribute("avatar", user.getAvatar());

                // Tạo cookie cho tên người dùng (không lưu mật khẩu)
                Cookie u = new Cookie("user", email);
                u.setMaxAge(60);  // Cookie tồn tại trong 60 giây
                response.addCookie(u);
                session.setAttribute("isAdmin", user.isIsAdmin());
                // Chuyển hướng người dùng đến trang chính
                response.sendRedirect("Home");  // Dùng sendRedirect thay vì forward
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
