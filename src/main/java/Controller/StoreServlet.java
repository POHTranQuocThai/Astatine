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
        
       // Phân trang
        String page = request.getParameter("page");


        int indexPage = 1;
        try {
            indexPage = (page == null || page.trim().isEmpty()) ? 1 : Integer.parseInt(page);
            if (indexPage < 1) {
                indexPage = 1;
            }
        } catch (NumberFormatException e) {
            indexPage = 1;
        }

        int minPrice = 0, maxPrice = Integer.MAX_VALUE;
        try {
            if (minPriceParam != null && !minPriceParam.isEmpty()) {
                minPrice = Integer.parseInt(minPriceParam);
            }
            if (maxPriceParam != null && !maxPriceParam.isEmpty()) {
                maxPrice = Integer.parseInt(maxPriceParam);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();

        }

        List<Products> list = Collections.emptyList();
        int totalPages = 0;
        int itemsPerPage = 12;

        if (search != null && !search.isEmpty()) {
            System.out.println("Performing search for: " + search + " with option: " + option + " and show: " + show);
            list = pDAO.searchProductByName(search, indexPage, option, show);
            if (list.isEmpty()) {
                request.setAttribute("noProductsMessage", "Không tìm thấy sản phẩm.");
                System.out.println("No products found for search: " + search);
            } else {
                System.out.println("Found " + list.size() + " products for search: " + search);
                int totalItems = pDAO.searchProductByName(search, 1, null, null).size();
                totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
                if (indexPage > totalPages) {
                    indexPage = totalPages;
                }
            }
        } else if (category != null && !category.isEmpty()) {
            System.out.println("Filtering by category: " + category + " with option: " + option + " and show: " + show);
            list = pDAO.getProductTypeSame(category, indexPage, option, show);
            totalPages = pDAO.getNumberPage(null, category); // Vẫn dùng getNumberPage với category
            if (list.isEmpty()) {
                request.setAttribute("noProductsMessage", "Không tìm thấy sản phẩm cho danh mục " + category + ".");
                System.out.println("No products found for category: " + category);
            } else {
                System.out.println("Found " + list.size() + " products for category: " + category);
            }
        } else if (brand != null && !brand.isEmpty()) {
            System.out.println("Filtering by brand: " + brand + " with option: " + option + " and show: " + show);
            list = pDAO.getProductBrandSame(brand, indexPage, option, show);
            if (list.isEmpty()) {
                request.setAttribute("noProductsMessage", "Không tìm thấy sản phẩm cho thương hiệu " + brand + ".");
                System.out.println("No products found for brand: " + brand);
            } else {
                System.out.println("Found " + list.size() + " products for brand: " + brand);
                // Sử dụng getNumberPage với tham số brand
                totalPages = pDAO.getNumberPage(brand, null); // Truyền brand, null cho category
                if (indexPage > totalPages) {
                    indexPage = totalPages;
                }
            }
        } else if (minPriceParam != null && maxPriceParam != null && !minPriceParam.isEmpty() && !maxPriceParam.isEmpty()) {
            System.out.println("Filtering by price range: " + minPrice + " - " + maxPrice + " with option: " + option + " and show: " + show);
            list = pDAO.getByPriceProduct(minPrice, maxPrice, indexPage, option, show); // Lấy sản phẩm cho trang hiện tại
            if (list.isEmpty()) {
                request.setAttribute("noProductsMessage", "Không tìm thấy sản phẩm trong khoảng giá " + minPrice + " - " + maxPrice + ".");
                System.out.println("No products found for price range: " + minPrice + " - " + maxPrice);
            } else {
                System.out.println("Found " + list.size() + " products for price range: " + minPrice + " - " + maxPrice);
                int totalItems = pDAO.getTotalProductsByPriceRange(minPrice, maxPrice); // Sử dụng phương thức mới để lấy tổng số
                System.out.println("Total items in price range: " + totalItems);
                totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
                System.out.println("Total pages: " + totalPages);
                if (indexPage > totalPages) {
                    indexPage = totalPages;
                }
            }
        } else if (option != null && show != null && !option.isEmpty() && !show.isEmpty()) {
            if (option.equals("sortName") && show.equals("up")) {
                list = pDAO.sortByNameUp(indexPage);
            } else if (option.equals("sortName") && show.equals("down")) {
                list = pDAO.sortByNameDown(indexPage);
            } else if (option.equals("sortPrice") && show.equals("up")) {
                list = pDAO.sortByPriceUp(indexPage);
            } else if (option.equals("sortPrice") && show.equals("down")) {
                list = pDAO.sortByPriceDown(indexPage);
            }
            totalPages = pDAO.getNumberPage(null, null);
        } else {
            list = pDAO.getPaging(indexPage, null, null);
            totalPages = pDAO.getNumberPage(null, null);
        }

        // Debug danh sách sản phẩm
        for (Products p : list) {
            System.out.println("Product ID: " + p.getProductId() + " - Name: " + p.getProductName());
        }

        System.out.println("Total pages (numberPage): " + totalPages);

        // Truyền dữ liệu vào request
        request.setAttribute("products", list);
        request.setAttribute("brand", pDAO.getAllBrand());
        request.setAttribute("type", pDAO.getAllType());
        request.setAttribute("topSelled", pDAO.getTopSelled());
        request.setAttribute("indexPage", indexPage);
        request.setAttribute("numberPage", totalPages);
        request.setAttribute("selectedCategory", category);
        request.setAttribute("selectedBrand", brand);

        // Chuyển tiếp sang trang store.jsp
        request.getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Cập nhật từ đoạn code 1
    }

    @Override
    public String getServletInfo() {
        return "Short description";

    }
}

