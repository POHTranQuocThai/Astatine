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
import model.Cart;
import model.Products;
import model.User;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class HomeServlet extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // Ghi đè phương thức doGet của HttpServlet để xử lý các yêu cầu GET từ client
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Tạo một đối tượng ProductDAO để tương tác với cơ sở dữ liệu
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
                request.setAttribute("userName", user.getFullname());
            }
        }
        // Nếu cDAO chưa tồn tại trong session, khởi tạo mới
        if (cDAO == null) {
            cDAO = new CartDAO();
            if (!oDAO.getProductByUserId(userId).isEmpty()) {
                for (Products p : oDAO.getProductByUserId(userId)) {
                    if (!p.getStatus().equals("Processing")) {
                        Cart c = new Cart(p, p.getQuanOrder());
                        cDAO.addToCart(c);
                        
                    }
                }
                session.setAttribute("SHOP", cDAO);  // Lưu cDAO vào session
            }
        }

        // Lấy danh sách order cũ và thêm vào giỏ hàng (chỉ khi chưa có)
        // Kiểm tra nếu cả danh sách tất cả sản phẩm và danh sách sản phẩm bán chạy không rỗng
        if (!pDAO.getAll().isEmpty() && !pDAO.getTopSelled().isEmpty()) {
            // Gắn danh sách tất cả sản phẩm vào request với tên "products"
            request.setAttribute("products", pDAO.getAll());

            request.setAttribute("numOrder", oDAO.getProductByUserId(userId).size());
            // Gắn danh sách sản phẩm bán chạy vào request với tên "topSelled"
            request.setAttribute("topSelled", pDAO.getTopSelled());

            request.setAttribute("brand", pDAO.getAllBrand());
            request.setAttribute("type", pDAO.getAllType());

        }
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
        HttpSession session = request.getSession(false); // false để không tạo mới nếu không có      
        OrderDAO oDAO = new OrderDAO();
        int userId = 0;
        if (session != null) {
            CartDAO cDAO = (CartDAO) session.getAttribute("SHOP");
            User user = (User) session.getAttribute("User");
            if (user != null) {
                userId = user.getUserId();
                request.setAttribute("userName", user.getFullname());
            }
        }
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
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