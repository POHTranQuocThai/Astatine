/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Products;
import model.Type;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Products;
import model.Type;


/**
 *
 * @author Tran Quoc Thai - CE181618
 */
// Lớp ProductDAO kế thừa từ DBContext để tương tác với cơ sở dữ liệu
public class ProductDAO extends DBContext {

    // Phương thức main để kiểm tra hoạt động của DAO
    public static void main(String[] args) {
        ProductDAO p = new ProductDAO();
        // In danh sách tất cả sản phẩm ra console
        System.out.println(p.getAll());
    }

    // Phương thức để lấy tất cả sản phẩm từ cơ sở dữ liệu
    public ArrayList<Products> getAll() {
        // Tạo một danh sách trống để lưu các sản phẩm
        ArrayList<Products> prod = new ArrayList<>();

        // Câu truy vấn SQL để chọn tất cả các cột từ bảng 'products'
        String query = "SELECT P.*, C.Category_Name, \n"

                + "                ISNULL(B.brand_name, 'ABCX') AS brand_name\n"
                + "              FROM \n"
                + "                  Products P\n"
                + "               JOIN \n"
                + "                  Brands B ON P.brand_id = B.brand_id\n"
                + "				  join\n"
                + "				  Categories C on P.Category_Id = C.Category_Id";


        // Thực thi truy vấn và lấy kết quả trả về
        try ( ResultSet rs = execSelectQuery(query)) {
            // Lặp qua từng hàng trong tập kết quả
            while (rs.next()) {
                // Tạo đối tượng Products bằng dữ liệu từ từng cột và thêm vào danh sách
                String[] image = rs.getString(8).split(","); // Tách chuỗi hình ảnh
                prod.add(new Products(
                        rs.getInt(1), // Cột ID
                        rs.getString(2), // Cột tên sản phẩm
                        rs.getInt(5), // Cột số lượng
                        rs.getInt(6), // Cột selled
                        rs.getDouble(7), // Cột giá
                        image[0], // Mảng hình ảnh từ cột hình ảnh
                        rs.getString(9), // Cột tên thương hiệu
                        rs.getString(10), // Cột trạng thái
                        rs.getString(11) // Cột mô tả
                ));
            }
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace(); // Hiển thị thông báo lỗi nếu xảy ra
        }
        return prod; // Trả về danh sách các sản phẩm
    }

    // Phương thức để lấy các sản phẩm bán chạy nhất từ cơ sở dữ liệu
    public ArrayList<Products> getTopSelled() {
        // Tạo một danh sách trống để lưu các sản phẩm bán chạy
        ArrayList<Products> prod = new ArrayList<>();

        // Câu truy vấn SQL để chọn tất cả sản phẩm và sắp xếp theo giá giảm dần
        String query = "SELECT P.*,  C.Category_Name,\n"
                + "ISNULL(B.brand_name, 'ABCX') AS brand_name\n"
                + "FROM Products P\n"
                + "JOIN Brands B ON P.brand_id = B.brand_id\n"
                + "join Categories C on P.Category_Id = C.Category_Id\n"
                + "order by P.Sold desc";

        // Thực thi truy vấn và lấy kết quả trả về
        try ( ResultSet rs = execSelectQuery(query)) {
            // Lặp qua từng hàng trong tập kết quả
            while (rs.next()) {
                String[] image = rs.getString(5).split(","); // Tách chuỗi hình ảnh
                // Tạo đối tượng Products bằng dữ liệu từ từng cột và thêm vào danh sách
                prod.add(new Products(
                        rs.getInt(1), // Cột ID
                        rs.getString(2), // Cột tên sản phẩm
                        rs.getInt(5), // Cột số lượng
                        rs.getInt(6), // Cột selled
                        rs.getDouble(7), // Cột giá
                        image[0], // Mảng hình ảnh từ cột hình ảnh
                        rs.getString(9), // Cột tên thương hiệu
                        rs.getString(10), // Cột trạng thái
                        rs.getString(11)
                ));
            }
        } catch (Exception e) {
            // Xử lý lỗi nếu có
        }
        return prod; // Trả về danh sách sản phẩm bán chạy
    }

    public Products getProductById(int id) {
        String query = "SELECT P.*, C.Category_Name, B.brand_name\n"
                + "FROM Products P\n"

                + "JOIN Brands B ON P.brand_id = B.brand_id\n"
                + "JOIN Categories C ON P.Category_Id = C.Category_Id\n"
                + "LEFT JOIN order_details o ON P.product_id = o.product_id\n"
                + "WHERE P.Product_ID = ?";
        Object[] params = {id};
        try ( ResultSet rs = execSelectQuery(query, params)) {
            if (rs.next()) {
                String[] image = rs.getString(8).split(",");
                return new Products(
                        rs.getInt(1), rs.getString(2), rs.getInt(5), rs.getInt(6),
                        rs.getDouble(7), image[0], rs.getString(9), rs.getString(10), rs.getString(11)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Products> getProductTypeSame(int id) {
        ArrayList<Products> prod = new ArrayList<>();
        String query = "SELECT TOP 4 P2.*, B.brand_name, C.Category_Name\n"
                + "FROM Products P1\n"
                + "JOIN Products P2 ON P1.Category_Id = P2.Category_Id\n"
                + "JOIN Brands B ON P2.brand_id = B.brand_id\n"
                + "JOIN Categories C ON P2.Category_Id = C.Category_Id\n"
                + "WHERE P1.product_id = ?\n"
                + "AND P2.product_id != ?\n"
                + "ORDER BY P2.Product_ID";  // Câu lệnh SQL vẫn giữ nguyên

        Object[] params = {id, id};  // Truyền giá trị limit vào

        try ( ResultSet rs = execSelectQuery(query, params)) {
            while (rs.next()) {
                String[] image = rs.getString(8).split(",");  // Tách chuỗi hình ảnh
                prod.add(new Products(rs.getInt(1), rs.getString(2), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
                System.out.println("id:" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();  // In ra lỗi để dễ dàng xác định nếu có
        }

        return prod;
    }


    public ArrayList<Brand> getAllBrand() {
        ArrayList<Brand> brand = new ArrayList<>();
        // Câu truy vấn SQL để chọn tất cả các cột từ bảng 'products'
        String query = "SELECT * FROM Brands";

        // Thực thi truy vấn và lấy kết quả trả về
        try ( ResultSet rs = execSelectQuery(query)) {
            // Lặp qua từng hàng trong tập kết quả
            while (rs.next()) {
                // Tạo đối tượng Products bằng dữ liệu từ từng cột và thêm vào danh sách
                brand.add(new Brand(
                        rs.getInt(1), // Cột ID
                        rs.getString(2), // Cột tên sản phẩm
                        rs.getString(3) // Cột mô tả
                ));
            }
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace(); // Hiển thị thông báo lỗi nếu xảy ra
        }
        return brand; // Trả về danh sách các sản phẩm
    }

    public ArrayList<Type> getAllType() {
        ArrayList<Type> type = new ArrayList<>();
        // Câu truy vấn SQL để chọn tất cả các cột từ bảng 'products'
        String query = "SELECT DISTINCT Category_Name\n"
                + "FROM Categories\n"
                + "WHERE Category_Name IN ('Racket', 'Shoes', 'Accessories');";

        // Thực thi truy vấn và lấy kết quả trả về
        try ( ResultSet rs = execSelectQuery(query)) {
            // Lặp qua từng hàng trong tập kết quả
            while (rs.next()) {
                // Tạo đối tượng Products bằng dữ liệu từ từng cột và thêm vào danh sách
                type.add(new Type(
                        rs.getString(1)// Cột tên sản phẩm                     
                ));
            }
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace(); // Hiển thị thông báo lỗi nếu xảy ra
        }
        return type; // Trả về danh sách các sản phẩm
    }


    public ArrayList<Products> getProductTypeSame(String category, int page, String option, String show) {
        ArrayList<Products> type = new ArrayList<>();
        int itemsPerPage = 12; // Đồng bộ với getNumberPage
        int offset = (page - 1) * itemsPerPage; // Tính offset

        String orderByClause = "";
        if (option != null && show != null) {
            if (option.equals("sortName") && show.equals("up")) {
                orderByClause = "ORDER BY p.Product_Name ASC";
            } else if (option.equals("sortName") && show.equals("down")) {
                orderByClause = "ORDER BY p.Product_Name DESC";
            } else if (option.equals("sortPrice") && show.equals("up")) {
                orderByClause = "ORDER BY p.Price ASC";
            } else if (option.equals("sortPrice") && show.equals("down")) {
                orderByClause = "ORDER BY p.Price DESC";
            }
        }
        if (orderByClause.isEmpty()) {
            orderByClause = "ORDER BY p.Product_ID ASC"; // Mặc định
        }


        String query = "SELECT P.*, B.Brand_Name, C.Category_Name\n"
                + "FROM Products P\n"
                + "JOIN Brands B ON P.brand_id = B.brand_id\n"
                + "JOIN Categories C ON P.Category_Id = C.Category_Id\n"
                + "WHERE C.Category_Name = ?\n"

                + orderByClause + "\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";

        Object[] params = {category, offset, itemsPerPage};
        try ( ResultSet rs = execSelectQuery(query, params)) {
            while (rs.next()) {
                String[] image = rs.getString(8).split(",");
                type.add(new Products(
                        rs.getInt(1), // Product_ID
                        rs.getString(2), // Product_Name
                        rs.getInt(5), // CountInStock
                        rs.getInt(6), // Selled
                        rs.getDouble(7), // Price
                        image[0], // Image
                        rs.getString(9), // Brand_Name
                        rs.getString(10), // Category_Name
                        rs.getString(11) // Description
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    // Phương thức để lấy tất cả sản phẩm từ cơ sở dữ liệu
    public ArrayList<Products> getByPriceProduct(int min, int max) {
        ArrayList<Products> prod = new ArrayList<>();

        String query = "SELECT P.*, ISNULL(B.brand_name, 'ABCX') AS brand_name, C.Category_Name\n"
                + "FROM Products P\n"
                + "JOIN Brands B ON P.brand_id = B.brand_id\n"
                + "JOIN Categories C ON P.Category_Id = C.Category_Id\n"
                + "WHERE P.price BETWEEN ? AND ?"; // Thêm khoảng trắng

        Object[] params = {min, max};
        System.out.println("Executing query with min: " + min + ", max: " + max);

        try ( ResultSet rs = execSelectQuery(query, params)) {
            while (rs.next()) {
                String[] image = rs.getString(8).split(","); // Tách chuỗi hình ảnh
                prod.add(new Products(
                        rs.getInt(1), // Cột ID
                        rs.getString(2), // Cột tên sản phẩm
                        rs.getInt(5), // Cột số lượng
                        rs.getInt(6), // Cột selled
                        rs.getDouble(7), // Cột giá
                        image[0], // Mảng hình ảnh từ cột hình ảnh
                        rs.getString(9), // Cột tên thương hiệu
                        rs.getString(10), // Cột trạng thái
                        rs.getString(11) // Cột mô tả
                ));
            }
            System.out.println("Found products: " + prod.size());
        } catch (Exception e) {
            System.out.println("Error executing query: " + query);
            e.printStackTrace();
        }
        return prod;
    }


    public ArrayList<Products> getProductBrandSame(String brand, int page, String option, String show) {
        ArrayList<Products> type = new ArrayList<>();
        int itemsPerPage = 12;
        int offset = (page - 1) * itemsPerPage;
        System.out.println("Page: " + page + ", Offset: " + offset + ", Items per page: " + itemsPerPage);

        String orderByClause = "";
        if (option != null && show != null) {
            if (option.equals("sortName") && show.equals("up")) {
                orderByClause = "ORDER BY p.Product_Name ASC";
            } else if (option.equals("sortName") && show.equals("down")) {
                orderByClause = "ORDER BY p.Product_Name DESC";
            } else if (option.equals("sortPrice") && show.equals("up")) {
                orderByClause = "ORDER BY p.Price ASC";
            } else if (option.equals("sortPrice") && show.equals("down")) {
                orderByClause = "ORDER BY p.Price DESC";
            }
        }
        if (orderByClause.isEmpty()) {
            orderByClause = "ORDER BY p.Product_ID ASC";
        }

        String query = "SELECT P.*, B.Brand_Name, C.Category_Name\n"

                + "FROM Products P\n"
                + "JOIN Brands B ON P.brand_id = B.brand_id\n"
                + "JOIN Categories C ON P.Category_Id = C.Category_Id\n"
                + "WHERE B.Brand_Name = ?\n"
                + orderByClause + "\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";

        Object[] params = {brand, offset, itemsPerPage};
        try ( ResultSet rs = execSelectQuery(query, params)) {
            while (rs.next()) {
                String[] image = rs.getString(8).split(",");
                Products product = new Products(
                        rs.getInt(1), rs.getString(2), rs.getInt(5), rs.getInt(6), rs.getDouble(7),
                        image[0], rs.getString(9), rs.getString(10), rs.getString(11)
                );
                type.add(product);
                System.out.println("Product: " + product.getProductName() + ", Category: " + product.getType());
            }
            System.out.println("Total products retrieved: " + type.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    public ArrayList<Products> getByPriceProduct(int min, int max, int page, String option, String show) {
        ArrayList<Products> prod = new ArrayList<>();
        int itemsPerPage = 12;
        int offset = (page - 1) * itemsPerPage;

        // Xác định cách sắp xếp dựa trên option và show
        String orderByClause = "";
        if (option != null && show != null) {
            if (option.equals("sortName") && show.equals("up")) {
                orderByClause = "ORDER BY p.Product_Name ASC";
            } else if (option.equals("sortName") && show.equals("down")) {
                orderByClause = "ORDER BY p.Product_Name DESC";
            } else if (option.equals("sortPrice") && show.equals("up")) {
                orderByClause = "ORDER BY p.Price ASC";
            } else if (option.equals("sortPrice") && show.equals("down")) {
                orderByClause = "ORDER BY p.Price DESC";
            }
        }
        if (orderByClause.isEmpty()) {
            orderByClause = "ORDER BY p.Product_ID ASC"; // Mặc định nếu không có sắp xếp
        }

        String query = "SELECT P.*, ISNULL(B.brand_name, 'ABCX') AS brand_name, C.Category_Name\n"
                + "FROM Products P\n"
                + "JOIN Brands B ON P.brand_id = B.brand_id\n"
                + "JOIN Categories C ON P.Category_Id = C.Category_Id\n"
                + "WHERE P.price BETWEEN ? AND ?\n"
                + orderByClause + "\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";

        Object[] params = {min, max, offset, itemsPerPage};
        System.out.println("Executing query with min: " + min + ", max: " + max + ", page: " + page);

        try ( ResultSet rs = execSelectQuery(query, params)) {
            while (rs.next()) {
                String[] image = rs.getString(8).split(",");
                prod.add(new Products(
                        rs.getInt(1), // Product_ID
                        rs.getString(2), // Product_Name
                        rs.getInt(5), // CountInStock
                        rs.getInt(6), // Selled
                        rs.getDouble(7), // Price
                        image[0], // Image
                        rs.getString(9), // Brand_Name
                        rs.getString(10),// Category_Name
                        rs.getString(11) // Description
                ));
            }
            System.out.println("Found products: " + prod.size());
        } catch (Exception e) {
            System.out.println("Error executing query: " + query);
            e.printStackTrace();
        }
        return prod;
    }

    public ArrayList<Products> sortByPriceDown(int page) {
        ArrayList<Products> prod = new ArrayList<>();
        int itemsPerPage = 12;
        int offset = (page - 1) * itemsPerPage;

        String query = "SELECT p.*, b.Brand_Name, c.Category_Name\n"
                + "FROM Products p\n"
                + "JOIN Brands b ON b.Brand_ID = p.Brand_ID\n"
                + "JOIN Categories c ON p.Category_Id = c.Category_Id\n"
                + "ORDER BY p.Price DESC\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, offset);
            ps.setInt(2, itemsPerPage);
            System.out.println("Executing query: " + query);
            System.out.println("Params: offset=" + offset + ", itemsPerPage=" + itemsPerPage);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String[] image = rs.getString(8).split(",");
                    prod.add(new Products(
                            rs.getInt(1), // Product_ID
                            rs.getString(2), // Product_Name
                            rs.getInt(5), // CountInStock
                            rs.getInt(6), // Selled
                            rs.getDouble(7), // Price
                            image[0], // Image
                            rs.getString(9), // Brand_Name
                            rs.getString(10), // Category_Name
                            rs.getString(11) // Description
                    ));
                }
                System.out.println("Result size: " + prod.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error executing query: " + e.getMessage());
        }
        return prod;
    }

    public ArrayList<Products> sortByPriceUp(int page) {
        ArrayList<Products> prod = new ArrayList<>();
        int itemsPerPage = 12;
        int offset = (page - 1) * itemsPerPage;
        String query = "SELECT p.*, b.Brand_Name, c.Category_Name\n"
                + "FROM Products p\n"
                + "JOIN Brands b ON b.Brand_ID = p.Brand_ID\n"
                + "JOIN Categories c ON p.Category_Id = c.Category_Id\n"
                + "ORDER BY p.Price ASC\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, offset);
            ps.setInt(2, itemsPerPage);
            System.out.println("Executing query: " + query);
            System.out.println("Params: offset=" + offset + ", itemsPerPage=" + itemsPerPage);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String[] image = rs.getString(8).split(",");
                    prod.add(new Products(
                            rs.getInt(1), // Product_ID
                            rs.getString(2), // Product_Name
                            rs.getInt(5), // CountInStock
                            rs.getInt(6), // Selled
                            rs.getDouble(7), // Price
                            image[0], // Image
                            rs.getString(9), // Brand_Name
                            rs.getString(10), // Category_Name
                            rs.getString(11) // Description
                    ));
                }
                System.out.println("Result size: " + prod.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error executing query: " + e.getMessage());
        }
        return prod;
    }

    public ArrayList<Products> sortByNameUp(int page) {
        ArrayList<Products> prod = new ArrayList<>();
        int itemsPerPage = 12;
        int offset = (page - 1) * itemsPerPage;
        String query = "SELECT p.*, b.Brand_Name, c.Category_Name\n"
                + "FROM Products p\n"
                + "JOIN Brands b ON b.Brand_ID = p.Brand_ID\n"
                + "JOIN Categories c ON p.Category_Id = c.Category_Id\n"
                + "ORDER BY p.Product_Name ASC\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, offset);
            ps.setInt(2, itemsPerPage);
            System.out.println("Executing query: " + query);
            System.out.println("Params: offset=" + offset + ", itemsPerPage=" + itemsPerPage);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String[] image = rs.getString(8).split(",");
                    prod.add(new Products(
                            rs.getInt(1), // Product_ID
                            rs.getString(2), // Product_Name
                            rs.getInt(5), // CountInStock
                            rs.getInt(6), // Selled
                            rs.getDouble(7), // Price
                            image[0], // Image
                            rs.getString(9), // Brand_Name
                            rs.getString(10), // Category_Name
                            rs.getString(11) // Description
                    ));
                }
                System.out.println("Result size: " + prod.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error executing query: " + e.getMessage());
        }
        return prod;
    }

    public ArrayList<Products> sortByNameDown(int page) {
        ArrayList<Products> prod = new ArrayList<>();
        int itemsPerPage = 12;
        int offset = (page - 1) * itemsPerPage;
        String query = "SELECT p.*, b.Brand_Name, c.Category_Name\n"
                + "FROM Products p\n"
                + "JOIN Brands b ON b.Brand_ID = p.Brand_ID\n"
                + "JOIN Categories c ON p.Category_Id = c.Category_Id\n"
                + "ORDER BY p.Product_Name DESC\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, offset);
            ps.setInt(2, itemsPerPage);
            System.out.println("Executing query: " + query);
            System.out.println("Params: offset=" + offset + ", itemsPerPage=" + itemsPerPage);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String[] image = rs.getString(8).split(",");
                    prod.add(new Products(
                            rs.getInt(1), rs.getString(2), rs.getInt(5), rs.getInt(6),
                            rs.getDouble(7), image[0], rs.getString(9), rs.getString(10), rs.getString(11)
                    ));
                }
                System.out.println("Result size: " + prod.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error executing query: " + e.getMessage());
        }
        return prod;
    }

    public ArrayList<Products> searchProductByName(String search, int page, String option, String show) {
        ArrayList<Products> find = new ArrayList<>();
        int itemsPerPage = 12;
        int offset = (page - 1) * itemsPerPage;

        String orderBy = "";
        if (option != null && show != null) {
            if (option.equals("sortName") && show.equals("up")) {
                orderBy = "ORDER BY p.Product_Name ASC";
            } else if (option.equals("sortName") && show.equals("down")) {
                orderBy = "ORDER BY p.Product_Name DESC";
            } else if (option.equals("sortPrice") && show.equals("up")) {
                orderBy = "ORDER BY p.Price ASC";
            } else if (option.equals("sortPrice") && show.equals("down")) {
                orderBy = "ORDER BY p.Price DESC";
            }
        } else {
            orderBy = "ORDER BY p.Product_ID";
        }
        String query = "SELECT p.*, b.Brand_Name, c.Category_Name\n"
                + "FROM Products p \n"
                + "JOIN Brands b ON p.Brand_ID = b.Brand_ID\n"
                + "JOIN Categories c ON p.Category_Id = c.Category_Id\n"
                + "WHERE p.product_name LIKE '%' + ? + '%'\n"
                + orderBy + "\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";

        Object[] params = {search, offset, itemsPerPage};
        try ( ResultSet rs = execSelectQuery(query, params)) {
            while (rs.next()) {
                String[] image = (rs.getString(8) != null && rs.getString(8).contains(",")) ? rs.getString(8).split(",") : new String[]{""};
                find.add(new Products(
                        rs.getInt(1), rs.getString(2), rs.getInt(5), rs.getInt(6),
                        rs.getDouble(7), image[0], rs.getString(9), rs.getString(10), rs.getString(11)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return find;

    }

    public int getNumberPage(String brand, String category) {
        String query = "SELECT COUNT(*) FROM Products p "
                + "JOIN Brands b ON p.Brand_ID = b.Brand_ID "
                + "JOIN Categories c ON p.Category_Id = c.Category_Id ";
        List<String> conditions = new ArrayList<>();
        if (brand != null && !brand.isEmpty()) {
            conditions.add("b.Brand_Name = ?");
        }
        if (category != null && !category.isEmpty()) {
            conditions.add("c.Category_Name = ?");
        }
        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions);
        }

        Object[] params = new Object[]{};
        if (brand != null && !brand.isEmpty()) {
            params = new Object[]{brand};
        }
        if (category != null && !category.isEmpty()) {
            params = (params.length > 0) ? new Object[]{params[0], category} : new Object[]{category};
        }

        try ( ResultSet rs = execSelectQuery(query, params)) {
            if (rs.next()) {
                int total = rs.getInt(1);
                int countPage = total / 12;
                if (total % 12 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Hàm hien thi so san pham trong phân trang

    public List<Products> getPaging(int index, String brand, String category) {
        List<Products> list = new ArrayList<>();
        int pageSize = 12; // Số sản phẩm trên mỗi trang
        int offset = (index - 1) * pageSize; // Vị trí bắt đầu
        if (offset < 0) {
            offset = 0;
        }

        System.out.println("Received Index: " + index); // Log giá trị index
        System.out.println("Calculated Offset: " + offset);
        System.out.println("Page Size: " + pageSize);
        System.out.println("Brand: " + brand);
        System.out.println("Category: " + category);

     
        String query = "SELECT p.*, b.Brand_Name, c.Category_Name FROM Products p "
                + "JOIN Brands b ON p.Brand_ID = b.Brand_ID "
                + "JOIN Categories c ON p.Category_Id = c.Category_Id ";


        // Danh sách các điều kiện lọc
        List<String> conditions = new ArrayList<>();

        // Thêm điều kiện lọc theo brand nếu có
        if (brand != null && !brand.isEmpty()) {
            conditions.add("b.Brand_Name = ?");
        }

        // Thêm điều kiện lọc theo category nếu có
        if (category != null && !category.isEmpty()) {
            conditions.add("c.Category_Name = ?");
        }

        // Kết hợp các điều kiện vào câu truy vấn
        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions);
        }

        // Thêm ORDER BY và phân trang
        query += " ORDER BY p.Product_ID ASC "

                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            int paramIndex = 1;

            // Thiết lập giá trị cho các tham số trong điều kiện lọc
            if (brand != null && !brand.isEmpty()) {
                ps.setString(paramIndex++, brand);
            }
            if (category != null && !category.isEmpty()) {
                ps.setString(paramIndex++, category);
            }

            // Thiết lập giá trị cho OFFSET và FETCH NEXT
            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex, pageSize);

            // Thực thi truy vấn và xử lý kết quả
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] image = rs.getString(8).split(",");
                list.add(new Products(
                        rs.getInt(1), // Product_ID
                        rs.getString(2), // Product_Name
                        rs.getInt(5), // Brand_ID
                        rs.getInt(6), // Category_Id
                        rs.getDouble(7), // Price
                        image[0], // Image (lấy ảnh đầu tiên)
                        rs.getString(9), // Brand_Name
                        rs.getString(10), // Category_Name
                        rs.getString(11) // Mô tả hoặc thông tin khác
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method to create a new product
    public int createProduct(Products product) {

        String createProductQuery = "INSERT INTO Products (Product_ID, Product_Name, type, countInStock, image, price, Selled, Brand_ID, description) "
                + "VALUES ((SELECT COALESCE(MAX(Product_ID), 0) + 1 FROM Products), ?, ?, ?, ?, ?, ?, "
                + "(SELECT Brand_ID FROM Brands WHERE Brand_Name = ?), ?)";
            Object[] params = {
            product.getProductName(),
            product.getType(),
            product.getCountInStock(),
            product.getImage(),
            product.getPrice(),
            product.getSelled(),
            product.getBrand(),
            product.getDescription()
        };
        try {
            return execQuery(createProductQuery, params);
        } catch (SQLException ex) {
            System.out.println("Error while creating product: " + ex.getMessage());
        }
        return 0;
    }

    // Method to update a product
    public int updateProduct(Products product) throws SQLException {

        String query = "UPDATE Products SET Product_Name = ?, type = ?, Brand_ID = (SELECT Brand_ID FROM Brands WHERE Brand_Name = ?), countInStock = ?, image = ?, Price = ?, Selled = ?, description = ? WHERE Product_ID = ?";
        Object[] params = {
            product.getProductName(),
            product.getType(),
            product.getBrand(),
            product.getCountInStock(),
            product.getImage(),
            product.getPrice(),
            product.getSelled(),
            product.getDescription(),
            product.getProductId()
        };
        return execQuery(query, params);
    }

    // Method to delete a product
    public int deleteProduct(int productId) {
        String query = "UPDATE Orders\n"
                + "SET Product_ID = NULL\n"
                + "WHERE Product_ID = ?\n"
                + "\n"

                + "DELETE FROM Products\n"
                + "WHERE Product_ID = ?";
        Object[] params = {productId};

        try {
            return execQuery(query, params);
        } catch (SQLException ex) {
            return 0;
        }
    }
    public List<Products> getPagingAd(int index) throws SQLException {
        String query = "SELECT \n"
                + "    P.*, \n"
                + "    ISNULL(B.brand_name, 'ABCX') AS brand_name,\n"
                + "    C.Category_Name\n"
                + "FROM \n"
                + "    Products P\n"
                + "LEFT JOIN \n"
                + "    Brands B ON P.brand_id = B.brand_id\n"
                + "LEFT JOIN \n"
                + "    Categories C ON P.Category_Id = C.Category_Id\n"
                + "ORDER BY \n"
                + "    P.Product_ID\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT 6 ROWS ONLY";
        List<Products> list = new ArrayList<>();
        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, (index - 1) * 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] image = rs.getString(8).split(",");
                list.add(new Products(
                        rs.getInt(1), // Cột ID
                        rs.getString(2), // Cột tên sản phẩm
                        rs.getInt(5), // Cột số lượng
                        rs.getInt(6), // Cột selled
                        rs.getDouble(7), // Cột giá
                        image[0], // Mảng hình ảnh từ cột hình ảnh
                        rs.getString(9), // Cột tên thương hiệu
                        rs.getString(10), // Cột trạng thái
                        rs.getString(11) // Cột mô tả
                ));

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
//    trong khoang gia co phan trang
    public int getTotalProductsByPriceRange(int minPrice, int maxPrice) {
        String query = "SELECT COUNT(*) FROM Products P "
                + "JOIN Brands B ON P.brand_id = B.brand_id "
                + "JOIN Categories C ON P.Category_Id = C.Category_Id "
                + "WHERE P.price BETWEEN ? AND ?";
        Object[] params = {minPrice, maxPrice};
        try ( ResultSet rs = execSelectQuery(query, params)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
