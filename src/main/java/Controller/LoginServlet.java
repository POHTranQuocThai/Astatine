
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import model.GoogleAccount;
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
import DAO.GoogleLogin;
import jakarta.servlet.annotation.WebServlet;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code != null && !code.isEmpty()) {
            String accessToken = GoogleLogin.getToken(code);
            GoogleAccount googlePojo = GoogleLogin.getUserInfo(accessToken);

            UserDAO uDAO = new UserDAO();
            User user = uDAO.getUserByEmail(googlePojo.getEmail());

            System.out.println("Google Account Info: " + googlePojo);
            System.out.println("User from DB: " + user);

            HttpSession session = request.getSession();

            if (user == null) {
                try {
                    User newUser = new User();
                    newUser.setFullname(googlePojo.getName());
                    newUser.setEmail(googlePojo.getEmail());
                    newUser.setPassword("");

                    System.out.println("User from DB: " + newUser);

                    uDAO.createGoogleUser(newUser);

                    user = uDAO.getUserByEmail(googlePojo.getEmail());

                    session.setAttribute("User", user);
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("isAdmin", user.isIsAdmin());

                    response.sendRedirect("Home");
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                session.setAttribute("User", user);
                session.setAttribute("email", user.getEmail());
                session.setAttribute("isAdmin", user.isIsAdmin());

                response.sendRedirect("Home");
            }
        } else {
            String action = request.getParameter("action");
            response.setContentType("text/html;charset=UTF-8");

            if (action != null) {
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");

        UserDAO uDAO = new UserDAO();

        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        try {
            if (!uDAO.login(email, uDAO.getHashPass(pass))) {
                request.setAttribute("mess", "Email or password invalid!");
                request.setAttribute("email", email);
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            } else {
                User user = new User(uDAO.getUserId(email), pass, email);
                user.setIsAdmin(uDAO.checkIsAdmin(email));
                HttpSession session = request.getSession();
                user = uDAO.getUserByEmail(email);
                session.setAttribute("email", user.getEmail());
                session.setAttribute("User", user);
                session.setAttribute("isAdmin", user.isIsAdmin());

                Cookie u = new Cookie("user", email);
                u.setMaxAge(60);
                response.addCookie(u);
                session.setAttribute("isAdmin", user.isIsAdmin());
                response.sendRedirect("Home");

            }
        } catch (NoSuchAlgorithmException | SQLException ex) {
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
