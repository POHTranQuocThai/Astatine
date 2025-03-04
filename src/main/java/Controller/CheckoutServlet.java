/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.VoucherDAO;
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
public class CheckoutServlet extends HttpServlet {

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
        VoucherDAO vDAO = new VoucherDAO();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String voucherId = request.getParameter("voucherId");
        String dis = request.getParameter("dis");
        int userId = 0;

        HttpSession session = request.getSession(); // false để không tạo mới nếu không có      
        OrderDAO oDAO = new OrderDAO();
        CartDAO cDAO = (CartDAO) session.getAttribute("SHOP");
        if (session != null) {
            User user = (User) session.getAttribute("User");
            if (user != null) {
                userId = user.getUserId();
            }
        }
        // Nếu cDAO chưa tồn tại trong session, khởi tạo mới
        if (cDAO == null) {
            cDAO = new CartDAO();
            if (!oDAO.getProductByUserId(userId).isEmpty()) {
                for (Products p : oDAO.getProductByUserId(userId)) {
                    if (p.getStatus().equals("Pending")) {
                        p.setCountInStock(p.getCountInStock() - p.getQuanOrder());
                        Cart c = new Cart(p, p.getQuanOrder());
                        cDAO.addToCart(c);
                    }
                }
                session.setAttribute("SHOP", cDAO);  // Lưu cDAO vào session

            }

        }
        // Kiểm tra và xử lý hành động "remove"
        if ("remove".equals(action) && id != null) {
            try {
                int productId = Integer.parseInt(id);
                int removeOrder = oDAO.removeOrder(userId, productId);
                cDAO.removeProductCart(productId);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ");
            }
        }

        if (voucherId != null || dis != null) {
            int discount = Integer.parseInt(dis);
            request.setAttribute("applied", "applied");
            request.setAttribute("discount", discount);
        }
        // Lấy danh sách order cũ và thêm vào giỏ hàng (chỉ khi chưa có)
        // Thiết lập thuộc tính cho checkout.jsp
        request.setAttribute("brand", pDAO.getAllBrand());
        request.setAttribute("type", pDAO.getAllType());
        request.setAttribute("vouchers", vDAO.getAllVoucher());
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String num = request.getParameter("num");
        int userId = 0;
        HttpSession session = request.getSession(false); // false để không tạo mới nếu không có      
        ProductDAO pDAO = new ProductDAO();
        OrderDAO oDAO = new OrderDAO();

        if (session != null) {
            User user = (User) session.getAttribute("User");
            if (user != null) {
                userId = user.getUserId();
            }
        }
        if (id != null && !id.isEmpty() && userId != 0) {  // Kiểm tra action và id
            // Đảm bảo id không null và hợp lệ
            CartDAO cDAO = (CartDAO) session.getAttribute("SHOP");
            if (cDAO == null) {  // Nếu cDAO chưa tồn tại trong session, khởi tạo mới
                cDAO = new CartDAO();
            }
            Products p = pDAO.getProductById(Integer.parseInt(id));  // Lấy sản phẩm dựa trên id
            if (p != null) {  // Kiểm tra xem sản phẩm có tồn tại không                     
                String[] image = p.getImage().split(",");
                p.setImage(image[0]);  // Lấy hình ảnh đầu tiên
                Cart c = new Cart(p, Integer.parseInt(num));
                cDAO.addToCart(c);  // Thêm sản phẩm vào giỏ hàng              
            }
            session.setAttribute("SHOP", cDAO);  // Lưu cDAO vào session         
        }
        request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);
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
