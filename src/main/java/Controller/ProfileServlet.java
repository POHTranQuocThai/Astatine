/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import model.Order;
import model.User;

/**
 *
 * @author Ma Tan Loc - CE181795
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)

public class ProfileServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String email = (String) session.getAttribute("email");

        if (email != null) {
            UserDAO uDao = new UserDAO();
            OrderDAO oDAO = new OrderDAO();
            User user = uDao.getUserByEmail(email);

            if (user != null) {
                if ("password".equals(action)) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/WEB-INF/profilePassword.jsp").forward(request, response);
                } else if ("edit".equals(action)) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
                } else if ("order".equals(action)) {
                    List<Order> orderList = oDAO.getOrderByEmail(email); // Retrieve list of orders
                    request.setAttribute("orderList", orderList);
                    request.getRequestDispatcher("/WEB-INF/profileOrder.jsp").forward(request, response);
                }
            } else {
                // Optionally, handle the case where the user is not found
            }
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login if email is not present
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
        UserDAO uDao = new UserDAO();

        String pass = request.getParameter("password");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if (action.equals("password")) {
            String password = request.getParameter("password");
        }

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String street = request.getParameter("street");
        String ward = request.getParameter("ward");
        String district = request.getParameter("district");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        // Lấy đường dẫn của dự án và lưu vào trong img/product/
        String applicationPath = getServletContext().getRealPath("");
        String uploadPath = applicationPath.replace("build\\", "") + "assets\\img\\user";

        System.out.println("IMG: " + uploadPath);

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }

        // Xử lý nhiều ảnh và nối đường dẫn bằng dấu phẩy
        StringBuilder imagePaths = new StringBuilder();
        for (Part filePart : request.getParts()) {
            if (filePart.getName().equals("avatar") && filePart.getSize() > 0) { // Kiểm tra part là hình ảnh
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên file
                filePart.write(uploadPath + File.separator + fileName); // Tiến hành lưu file

                if (imagePaths.length() > 0) {
                    imagePaths.append(","); // Thêm dấu phẩy nếu đã có ảnh trước đó
                }
                imagePaths.append("assets\\img\\product\\").append(fileName); // Lưu đường dẫn ảnh
            }
        }

        if (email != null) {
            try {
                User user = uDao.getUserByEmail(email);
                if (user != null) {
                    user.setFullname(fullname);
                    user.setStreet(street);
                    user.setWard(ward);
                    user.setDistrict(district);
                    user.setCity(city);
                    user.setCountry(country);
                    user.setPhone(phone);

                    int result = uDao.updateUser(user);
                    if (result > 0) {
                        // Successful update; redirect to the profile servlet to refresh the data
                        response.sendRedirect("Profile?action=edit"); // Redirect to GET method
                    } else {
                        // Update failed; set an error message in the session or request
                        request.setAttribute("message", "Failed to update profile.");
                        // Optionally, forward back to the profile.jsp to display the error message
                        request.setAttribute("user", user); // Pass the updated user back
                        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
                    }
                }
            } catch (SQLException e) {
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
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
