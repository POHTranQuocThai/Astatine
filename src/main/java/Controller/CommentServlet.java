/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CommentDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comment;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class CommentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CommentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int productId = Integer.parseInt(request.getParameter("product-id"));
        System.out.println("prodId" + productId);
        if (productId != 0) {
            // Gọi DAO để lấy danh sách comment từ DB
            CommentDAO comDAO = new CommentDAO();
            List<Comment> comments = comDAO.getCommentByProductId(productId);
            System.out.println("com" + comments);
            // Chuyển danh sách thành JSON và trả về client
            String json = new Gson().toJson(comments);
            PrintWriter out = response.getWriter();
            out.write("{\"comments\": " + json + "}");
            out.flush();
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
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            CommentDAO comDAO = new CommentDAO();
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();

            // Đọc dữ liệu JSON từ request
            StringBuilder sb = new StringBuilder();
            try ( BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }

            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);

            // Kiểm tra dữ liệu đầu vào
            if (jsonObject == null || !jsonObject.has("user_id") || !jsonObject.has("product_id") || !jsonObject.has("content")) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu dữ liệu đầu vào");
                return;
            }

            // Lấy giá trị từ JSON
            int userId = jsonObject.get("user_id").getAsInt();
            int productId = jsonObject.get("product_id").getAsInt();
            String content = jsonObject.get("content").getAsString();

            // Kiểm tra dữ liệu hợp lệ
            if (userId <= 0 || productId <= 0 || content.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dữ liệu không hợp lệ");
                return;
            }

            // Lưu comment vào DB
            int num = comDAO.saveCommentDB(userId, productId, content);
            Integer commentId = comDAO.getCommentId(userId, productId);

            // Kiểm tra commentId có null không
            if (commentId == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không thể lấy ID bình luận");
                return;
            }

            // 🔹 Trả về phản hồi JSON để tránh lỗi rỗng
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "Bình luận đã được ghi nhận!");
            jsonResponse.addProperty("comment_id", commentId);

            out.print(gson.toJson(jsonResponse));
            out.flush(); // Đảm bảo phản hồi được gửi về
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý cơ sở dữ liệu");
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
