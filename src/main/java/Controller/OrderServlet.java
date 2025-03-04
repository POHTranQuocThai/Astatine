/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.Products;
import model.User;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class OrderServlet extends HttpServlet {

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
        String total = request.getParameter("total");
        ProductDAO pDAO = new ProductDAO();
        OrderDAO oDAO = new OrderDAO();
        UserDAO uDAO = new UserDAO();
        int userId = 0;
        int num1 = 0;

        HttpSession session = request.getSession(false); // false để không tạo mới nếu không có       
        User user = (User) session.getAttribute("User");
        if (user != null) {
            userId = user.getUserId();
        }
        CartDAO cDAO = (CartDAO) session.getAttribute("SHOP");
        user = uDAO.getInfoUser(userId);

        // Kiểm tra thông tin người dùng
        if (user == null || total.equals("0")) {
            request.setAttribute("messOrder", "Not products in the cart!");
            request.getRequestDispatcher("Checkout").forward(request, response);
            return;
        }
        if (!uDAO.checkInfoUser(user)) {
            request.setAttribute("messInfo", "Please enter full infomations of user before the order!");
            request.getRequestDispatcher("Profile?action=edit").forward(request, response);
            return;
        }

        // Tạo đối tượng Order với thông tin người dùng
        Order order = new Order(user.getStreet(), user.getWard(), user.getDistrict(), user.getCity(),
                user.getCountry(), user.getPhone(), "Processing", Double.parseDouble(total), user.getEmail(), userId);

        // Kiểm tra và cập nhật từng sản phẩm trong giỏ hàng
        try {
            boolean stockAvailable = true;

            // Duyệt qua các sản phẩm trong giỏ hàng
            for (Order cartOrder : cDAO.getProductsInCart(userId)) {
                Products prod = pDAO.getProductById(cartOrder.getProductId());

                // Kiểm tra số lượng tồn kho
                if (prod != null && prod.getCountInStock() < cartOrder.getAmount()) {
                    request.setAttribute("messOrder", "Count in stock of product name " + prod.getProductName() + " is " + prod.getCountInStock());
                    stockAvailable = false;
                    break;
                }
            }

            if (stockAvailable && cDAO != null) {
                // Lưu giỏ hàng vào cơ sở dữ liệu và cập nhật thông tin đơn hàng
                oDAO.saveCartToDatabase(userId, cDAO);
                num1 = oDAO.orderedSuccess(userId, order, cDAO);
                System.out.println("num1" + num1);
                request.setAttribute("messOrderSuccess", "Order success");
                // Xóa giỏ hàng hoàn toàn
                cDAO.clear(); // Xóa toàn bộ sản phẩm
                session.removeAttribute("SHOP");  // Xóa session cũ

            } else {
                // Nếu tồn kho không đủ, chuyển hướng về trang checkout
                request.getRequestDispatcher("Checkout").forward(request, response);
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, "Error while processing order: ", ex);
        }

        // Chuyển hướng tới CheckoutServlet thay vì dùng forward để tránh lỗi
        request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);
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
