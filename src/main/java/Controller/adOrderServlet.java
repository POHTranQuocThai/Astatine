/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Order;

/**
 *
 * @author Ma Tan Loc - CE181795
 */
public class adOrderServlet extends HttpServlet {

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
        OrderDAO oDAO = new OrderDAO();

        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if (action == null) {
            action = "list";
        }

        if ("list".equals(action)) {
            List<Order> list = oDAO.getAllOrders();
            request.setAttribute("orderList", list);
            request.getRequestDispatcher("/WEB-INF/adOrder.jsp").forward(request, response);
        }

        if ("edit".equals(action) && idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                Order order = oDAO.getOrderByOrderId(id);

                if (order == null) {
                    // Nếu không tìm thấy đơn hàng với ID, chuyển hướng về trang danh sách
                    response.sendRedirect("Order?action=list");
                    return;
                }

                // Hiển thị thông tin đơn hàng lên form edit
                request.setAttribute("order", order);
                request.getRequestDispatcher("/WEB-INF/adOrderEdit.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Nếu ID không hợp lệ, chuyển hướng về trang danh sách
                response.sendRedirect("Order");
            }
        }
        else if ("delete".equals(action) && idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                Order order = oDAO.getOrderByOrderId(id);

                if (order == null) {
                    // Nếu không tìm thấy đơn hàng với ID, chuyển hướng về trang danh sách
                    response.sendRedirect("Order?action=list");
                    return;
                }

                // Hiển thị thông tin đơn hàng lên form edit
                request.setAttribute("order", order);
                request.getRequestDispatcher("/WEB-INF/adOrderDelete.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Nếu ID không hợp lệ, chuyển hướng về trang danh sách
                response.sendRedirect("Order");
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
        OrderDAO oDAO = new OrderDAO();
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if ("edit".equals(action)) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            String street = request.getParameter("street");
            String ward = request.getParameter("ward");
            String district = request.getParameter("district");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String status = request.getParameter("status");

            // Tạo đối tượng Order với thông tin mới và cập nhật vào cơ sở dữ liệu
            Order order = new Order(orderId, street, ward, district, city, country, status);
            oDAO.updateOrder(order);
            response.sendRedirect("Order");
        }
        if ("delete".equals(action)) {
            int id = Integer.parseInt(idParam);
            oDAO.deleteOrder(id);
            response.sendRedirect("Order");
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
