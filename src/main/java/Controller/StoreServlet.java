/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import model.Products;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class StoreServlet extends HttpServlet {

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
        String view = request.getParameter("view");
        String category = request.getParameter("category");
        String brand = request.getParameter("brand");
        String option = request.getParameter("option");
        String show = request.getParameter("show");
        String search = request.getParameter("search");

        // Lấy giá trị từ input 'price-min' và 'price-max', kiểm tra giá trị null hoặc rỗng
        String minPriceParam = request.getParameter("price-min");
        String maxPriceParam = request.getParameter("price-max");
        
        String page = request.getParameter("page");

        if (page == null || page.trim().isEmpty()) {
            page = "1";
        }
        int indexPage = Integer.parseInt(page);

        List<Products> list = pDAO.getPaging(indexPage);

        // Gán giá trị mặc định nếu input không có giá trị
        int minPrice = 0;
        int maxPrice = Integer.MAX_VALUE;

        if (minPriceParam != null && !minPriceParam.isEmpty()) {
            minPrice = Integer.parseInt(minPriceParam);
        }

        if (maxPriceParam != null && !maxPriceParam.isEmpty()) {
            maxPrice = Integer.parseInt(maxPriceParam);

        }
        List<Products> getPrice = pDAO.getByPriceProduct(minPrice, maxPrice);
        List<Products> getBrand = pDAO.getProductBrandSame(brand, Integer.parseInt(page));
        if ((minPriceParam != null && maxPriceParam != null) && !getPrice.isEmpty()) {
            request.setAttribute("getByPrice", getPrice);
        } else if ((minPriceParam != null && maxPriceParam != null) && getPrice.isEmpty()) {
            request.setAttribute("noProductsMessage", "Not found to products!.");
            request.setAttribute("type", pDAO.getAllType());
            request.setAttribute("brand", pDAO.getAllBrand());
            request.setAttribute("topSelled", pDAO.getTopSelled());

            System.out.println("noProductsMessage: " + request.getAttribute("noProductsMessage"));
            // Chuyển tiếp đến store.jsp để hiển thị thông báo
            request.getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
            return;
        }
        // Xử lý khi người dùng chọn lọc theo loại sản phẩm (category)
        if (category != null && !category.isEmpty()) {

            // Lọc sản phẩm theo loại và giá (nếu có giá trị minPrice và maxPrice)
            if ("store".equals(category)) {
                request.setAttribute("products", list);  // Hiển thị tất cả sản phẩm
            } else {
                request.setAttribute("category", pDAO.getProductTypeSame(category, Integer.parseInt(page)));
            }
            request.setAttribute("type", pDAO.getAllType());
            request.setAttribute("brand", pDAO.getAllBrand());
            request.setAttribute("topSelled", pDAO.getTopSelled());
            request.getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
            return;
        } else if (brand != null && !brand.isEmpty()) {
            if ("store".equals(brand)) {
                request.setAttribute("products", list);  // Hiển thị tất cả sản phẩm
            } else {
                if (!getBrand.isEmpty()) {
                    request.setAttribute("brands", pDAO.getProductBrandSame(brand, Integer.parseInt(page)));
                } else {
                    request.setAttribute("noProductsMessage", "Not found products!.");
                }
            }
            request.setAttribute("type", pDAO.getAllType());
            request.setAttribute("brand", pDAO.getAllBrand());
            request.setAttribute("topSelled", pDAO.getTopSelled());
            request.getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
            return;
        } else if (option != null && show != null && !option.isEmpty() && !show.isEmpty()) {

            List<Products> products;
            if (option.equals("sortName") && show.equals("up")) {
                products = pDAO.sortByNameUp();
            } else if (option.equals("sortName") && show.equals("down")) {
                products = pDAO.sortByNameDown();
            } else if (option.equals("sortPrice") && show.equals("up")) {
                products = pDAO.sortByPriceUp();
            } else if (option.equals("sortPrice") && show.equals("down")) {
                products = pDAO.sortByPriceDown();
            } else {
                products = Collections.emptyList(); // hoặc xử lý cho trường hợp không hợp lệ
            }

            if (products != null) {
                request.setAttribute("products", products);
            } else {
                // Xử lý trường hợp không có sản phẩm
                request.setAttribute("noProductsMessage", "Sorry, no products found.");

            }
            request.setAttribute("type", pDAO.getAllType());
            request.setAttribute("brand", pDAO.getAllBrand());
            request.setAttribute("topSelled", pDAO.getTopSelled());
            request.getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
            return;
        } else if (search != null && !search.isEmpty()) {
            List<Products> products = pDAO.searchProductByName(search);
            if (products.isEmpty()) {
                request.getRequestDispatcher("/WEB-INF/blank.jsp").forward(request, response);
                return;
            }
            if (products.isEmpty()) {
                // Gửi thông báo lỗi cho trang store.jsp
                request.setAttribute("noProductsMessage", "Không tìm thấy sản phẩm.");
                // Hiển thị lại store.jsp với thông báo
                request.setAttribute("products", Collections.emptyList()); // Đảm bảo danh sách sản phẩm rỗng
                request.setAttribute("type", pDAO.getAllType());
                request.setAttribute("brand", pDAO.getAllBrand());
                request.setAttribute("topSelled", pDAO.getTopSelled());
                request.getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
                return;
            }
            request.setAttribute("search", products);
            request.setAttribute("type", pDAO.getAllType());
            request.setAttribute("brand", pDAO.getAllBrand());
            request.setAttribute("topSelled", pDAO.getTopSelled());
            request.getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
            return;
        }

        // Xử lý view là store hoặc không có view (mặc định)
        if ("store".equals(category) || view == null || view.equals("store")) {
            // Truyền dữ liệu vào request
            request.setAttribute("products", list);
            request.setAttribute("brand", pDAO.getAllBrand());
            request.setAttribute("type", pDAO.getAllType());
            request.setAttribute("topSelled", pDAO.getTopSelled());

            // Chuyển tiếp sang trang store.jsp
            request.getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
        }
        request.setAttribute("indexPage", indexPage);
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
