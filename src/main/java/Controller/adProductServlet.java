/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Products;

/**
 *
 * @author Ma Tan Loc - CE181795
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)

public class adProductServlet extends HttpServlet {

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

        String action = request.getParameter("action");
        String idParam = request.getParameter("id");
        String indexPage = request.getParameter("index");

        request.setAttribute("types", pDAO.getAllType());
        request.setAttribute("brands", pDAO.getAllBrand());

        if (action == null) {
            action = "list";
        }

        if ("list".equals(action)) {
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);

            int count = pDAO.getNumberPage();
            int endPage = count / 3; // Adjust for 6 items per page
            if (count % 3 != 0) {
                endPage++;
            }
            List<Products> list = null;
            try {
                list = pDAO.getPagingAd(index);
            } catch (SQLException ex) {
                Logger.getLogger(adProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("listPr", list);
            request.setAttribute("endP", endPage);
            request.getRequestDispatcher("/WEB-INF/adListProduct.jsp").forward(request, response);
            return;
        }

        // Kiểm tra trường hợp ID không hợp lệ hoặc không tồn tại
        Products product = null;
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                product = pDAO.getProductById(id);
            } catch (NumberFormatException e) {
                // ID không phải là số, chuyển hướng về danh sách
                response.sendRedirect("Product?action=list");
                return;
            }
        }

        // Nếu product là null, chuyển hướng về danh sách sản phẩm
        if (product == null && !"create".equals(action)) {
            response.sendRedirect("Product?action=list");
            return;
        }

        // Xử lý các hành động khác
        switch (action) {
            case "create":
                request.getRequestDispatcher("/WEB-INF/adCreateProduct.jsp").forward(request, response);
                break;
            case "edit":
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/adEditProduct.jsp").forward(request, response);
                break;
            case "delete":
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/adDeleteProduct.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("Product?action=list");
                break;
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
        ProductDAO pDAO = new ProductDAO();
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if (action.equals("create")) {
            String productName = request.getParameter("productName");
            String type = request.getParameter("type");
            int countInStock = Integer.parseInt(request.getParameter("countInStock"));
            double price = Double.parseDouble(request.getParameter("price"));
            int selled = 0;
            String description = request.getParameter("description");
            String brand = request.getParameter("brand");

            // Lấy đường dẫn của dự án và lưu vào trong img/product/
            String applicationPath = getServletContext().getRealPath("");
            String uploadPath = applicationPath.replace("build\\", "") + "assets\\img\\product";

            System.out.println("IMG: " + uploadPath);

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }

            // Xử lý nhiều ảnh và nối đường dẫn bằng dấu phẩy
            StringBuilder imagePaths = new StringBuilder();
            for (Part filePart : request.getParts()) {
                if (filePart.getName().equals("image") && filePart.getSize() > 0) { // Kiểm tra part là hình ảnh
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên file
                    filePart.write(uploadPath + File.separator + fileName); // Tiến hành lưu file

                    if (imagePaths.length() > 0) {
                        imagePaths.append(","); // Thêm dấu phẩy nếu đã có ảnh trước đó
                    }
                    imagePaths.append("assets\\img\\product\\").append(fileName); // Lưu đường dẫn ảnh
                }
            }

            // Tạo product với chuỗi đường dẫn ảnh
            Products product = new Products(0, productName, countInStock, selled, price, imagePaths.toString(), description, type, brand);
            int result = pDAO.createProduct(product);
            if (result > 0) {
                response.sendRedirect("Product?action=list");  // Thành công
            } else {
                request.setAttribute("error", "Failed to create product.");
                request.getRequestDispatcher("adCreateProduct.jsp").forward(request, response);
            }
        }

        if (idParam != null && !idParam.isEmpty()) {

            if (action.equals("edit")) {
                int productId = Integer.parseInt(request.getParameter("id"));
                String productName = request.getParameter("productName");
                String type = request.getParameter("type");
                int countInStock = Integer.parseInt(request.getParameter("countInStock"));
                double price = Double.parseDouble(request.getParameter("price"));
                String brand = request.getParameter("brand");
                int selled = Integer.parseInt(request.getParameter("selled"));
                String description = request.getParameter("description");

                // Retrieve existing product to get current images
                Products existingProduct = pDAO.getProductById(productId);
                String currentImages = existingProduct.getImage(); // Existing images from the database

                // Initialize a variable for the final image paths
                StringBuilder imagePaths = new StringBuilder();

                // Process new images if uploaded, otherwise keep current images
                boolean newImagesUploaded = false;
                for (Part filePart : request.getParts()) {
                    if (filePart.getName().equals("image") && filePart.getSize() > 0) { // Check if the image part is uploaded
                        newImagesUploaded = true;
                        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                        // Define upload path
                        String applicationPath = getServletContext().getRealPath("");
                        String uploadPath = applicationPath.replace("build\\", "") + "assets\\img\\product";
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs(); // Create directory if it doesn't exist
                        }

                        // Save each file and append path to imagePaths
                        filePart.write(uploadPath + File.separator + fileName);
                        if (imagePaths.length() > 0) {
                            imagePaths.append(",");
                        }
                        imagePaths.append("assets\\img\\product\\").append(fileName);
                    }
                }

                // If no new images were uploaded, keep the current images
                String image = newImagesUploaded ? imagePaths.toString() : currentImages;
                Products product = new Products(0, productName, countInStock, selled, price, imagePaths.toString(), description, type, brand);
                try {
                    pDAO.updateProduct(product);
                    request.setAttribute("types", pDAO.getAllType());
                    request.setAttribute("brands", pDAO.getAllBrand());
                    response.sendRedirect("Product?action=list");
                } catch (SQLException ex) {
                    Logger.getLogger(adProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (action.equals("delete")) {
                int id = Integer.parseInt(idParam);
                pDAO.deleteProduct(id);
                response.sendRedirect("Product?action=list");
            }
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
