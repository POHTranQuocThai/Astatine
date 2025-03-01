/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ForgetTokenDAO;
import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TokenForgetPassword;
import model.User;
import model.resetService;

/**
 *
 * @author Ma Tan Loc - CE181795
 */
public class requestPasswordServlet extends HttpServlet {

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
        request.getRequestDispatcher("WEB-INF/requestPassword.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        //email co ton tai trong db
        User user = uDAO.getUserByEmail(email);
        if (user == null) {
            request.setAttribute("mess", "email khong ton tai");
            request.getRequestDispatcher("WEB-INF/requestPassword.jsp").forward(request, response);
            return;
        }
        resetService service = new resetService();
        String token = service.generateToken();

        String linkReset = "http://localhost:8080/resetPassword?token=" + token;

        TokenForgetPassword newTokenForget = new TokenForgetPassword(
                user.getUserId(), false, token, service.expireDateTime());

        //send link to this email
        ForgetTokenDAO daoToken = new ForgetTokenDAO();
        boolean isInsert = daoToken.insertTokenForget(newTokenForget);
        if (!isInsert) {
            request.setAttribute("mess", "have error in server");
            request.getRequestDispatcher("WEB-INF/requestPassword.jsp").forward(request, response);
            return;
        }
        boolean isSend = service.sendEmail(email, linkReset, user.getFullname());
        if (!isSend) {
            request.setAttribute("mess", "can not send request");
            request.getRequestDispatcher("WEB-INF/requestPassword.jsp").forward(request, response);
            return;
        }
        request.setAttribute("mess", "send request success");
        request.getRequestDispatcher("WEB-INF/requestPassword.jsp").forward(request, response);
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
