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
import java.util.List;
import model.Cart;
import model.Products;
import model.User;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class QuantityServlet extends HttpServlet {
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
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        ProductDAO pDAO = new ProductDAO();
        HttpSession session = request.getSession(false); // false để không tạo mới nếu không có
        OrderDAO oDAO = new OrderDAO();
        CartDAO cDAO = null;
        int userId = 0;

        if (session != null) {
            cDAO = (CartDAO) session.getAttribute("SHOP");
            User user = (User) session.getAttribute("User");
            if (user != null) {
                userId = user.getUserId();
            }
        }
        List<Products> listProd = oDAO.getProductByUserId(userId);
        int quan = 0;
        boolean found = false;
        if ("inc".equals(action)) {
            for (Products p : listProd) {
                if (id == p.getProductId()) {
                    quan = p.getQuanOrder();
                    // Nếu hành động là tăng
                    quan++;
                    p.setQuanOrder(quan);
                    String[] image = p.getImage().split(",");
                    p.setImage(image[0]);
                    // Thêm sản phẩm vào giỏ hàng với số lượng đã cập nhật
                    Cart c = new Cart(p);
                    cDAO.addToCart(c);  // Thêm sản phẩm vào giỏ hàng
                    found = true;
                    break;
                }
            }
            if (!found && id != 0 || listProd.isEmpty()) {
                Products prod = pDAO.getProductById(id);
                String[] image = prod.getImage().split(",");
                prod.setImage(image[0]);
                Cart c = new Cart(prod);
                cDAO.addToCart(c);  // Thêm sản phẩm vào giỏ hàng
                session.setAttribute("SHOP", cDAO);
            }
            response.sendRedirect("Checkout");
        }
        if ("dec".equals(action)) {
            for (Products p : listProd) {
                if (id == p.getProductId()) {
                    quan = p.getQuanOrder();
                    // Nếu hành động là giảm
                    if (quan > 1) {  // Đảm bảo số lượng không giảm xuống dưới 1
                        quan--;  // Giảm số lượng đi 1
                        p.setQuanOrder(quan);
                        String[] image = p.getImage().split(",");
                        p.setImage(image[0]);
                        // Thêm sản phẩm vào giỏ hàng với số lượng đã cập nhật          
                        Cart c = new Cart(p);
                        cDAO.decQuantityToCart(c);  // Thêm sản phẩm vào giỏ hàng
                        break;
                    }
                }
            }
            if (!found && id != 0 || listProd.isEmpty()) {
                Products prod = pDAO.getProductById(id);
                String[] image = prod.getImage().split(",");
                prod.setImage(image[0]);
                Cart c = new Cart(prod);
                cDAO.decQuantityToCart(c);  // Thêm sản phẩm vào giỏ hàng
                session.setAttribute("SHOP", cDAO);
            }
            response.sendRedirect("Checkout");
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
