/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class LogoutServlet extends HttpServlet {

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
        ProductDAO pDAO = new ProductDAO();
        OrderDAO oDAO = new OrderDAO();
        int userId = 0;
        int num = 0;
        String view = request.getParameter("view");
        HttpSession session = request.getSession(); // false để không tạo mới nếu không có       
        User user = (User) session.getAttribute("User");
        if (user != null) {
            userId = user.getUserId();
        }
        CartDAO cDAO = (CartDAO) session.getAttribute("SHOP");
        if (view.equals("logout")) {
            try {
                if (cDAO != null) {
                    num = oDAO.saveCartToDatabase(userId, cDAO);
                    System.out.println("num" + num);
                    session.removeAttribute("SHOP"); // Xóa giỏ hàng sau khi lưu                
                }
            } catch (SQLException ex) {
                Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, "Error while logging out: ", ex);
            }
        }
        if (session != null) {
            session.invalidate(); // Hủy session         
        }

        request.setAttribute("products", pDAO.getAll());

        // Gắn danh sách sản phẩm bán chạy vào request với tên "topSelled"
        request.setAttribute("topSelled", pDAO.getTopSelled());

        request.setAttribute("brand", pDAO.getAllBrand());
        request.setAttribute("type", pDAO.getAllType());
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
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
