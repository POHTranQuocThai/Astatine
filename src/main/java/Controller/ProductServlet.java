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
import model.Products;
import model.User;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class ProductServlet extends HttpServlet {


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
        // Tạo một đối tượng ProductDAO để tương tác với cơ sở dữ liệu
        ProductDAO pDAO = new ProductDAO();
        OrderDAO oDAO = new OrderDAO();
        String view = request.getParameter("view");
        int userId = 0;
        // Kiểm tra nếu cả danh sách tất cả sản phẩm và danh sách sản phẩm bán chạy không rỗng
        HttpSession session = request.getSession(); // false để không tạo mới nếu không có      
        CartDAO cDAO = (CartDAO) session.getAttribute("SHOP");
        if (view.equals("prod-details")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                if (cDAO != null) {
                    int num = oDAO.saveCartToDatabase(userId, cDAO);
                    System.out.println("num" + num);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, "Error while logging out: ", ex);
            }
            Products prod = pDAO.getProductById(id);
            request.setAttribute("prodDetails", prod);
            request.setAttribute("prodType", pDAO.getProductTypeSame(id));
            String[] image = prod.getImage().split(",");
            request.setAttribute("getImage", image);
            request.setAttribute("brand", pDAO.getAllBrand());
            request.setAttribute("type", pDAO.getAllType());
            request.getRequestDispatcher("/WEB-INF/product.jsp").forward(request, response);
            return;
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
