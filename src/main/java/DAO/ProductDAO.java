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
        String query = "SELECT P.*, C.Category_Name, \n"
                + "B.brand_name\n"
                + "FROM Products P\n"
                + "JOIN Brands B ON P.brand_id = B.brand_id\n"
                + "join Categories C on P.Category_Id = C.Category_Id\n"
                + "LEFT JOIN order_details o ON P.product_id = o.product_id\n"
                + "WHERE P.Product_ID = ?";

        Object[] params = {id};
        try ( ResultSet rs = execSelectQuery(query, params)) {
            // Lặp qua từng hàng trong tập kết quả
            if (rs.next()) {
                return new Products(rs.getInt(1), rs.getString(2), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));// Cột giá                       

            }
        } catch (Exception e) {
            // Xử lý lỗi nếu có
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
                System.out.println("id:"+id);
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

    public ArrayList<Products> getProductTypeSame(String category, int index) {
        ArrayList<Products> type = new ArrayList<>();

        // Câu truy vấn SQL để chọn tất cả các cột từ bảng 'products'
        String query = "SELECT P.*, B.Brand_Name, C.Category_Name\n"
                + "FROM Products P\n"
                + "JOIN Brands B ON P.brand_id = B.brand_id\n"
                + "JOIN Categories C ON P.Category_Id = C.Category_Id\n"
                + "WHERE C.Category_Name = ?\n"
                + "ORDER BY P.Product_ID\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT 6 ROWS ONLY";  // Sử dụng dấu ? cho tham số

        // Truyền trực tiếp tham số category vào mảng params
        Object[] params = {category, index};
        // Thực thi truy vấn và lấy kết quả trả về
        try ( ResultSet rs = execSelectQuery(query, params)) {
            // Lặp qua từng hàng trong tập kết quả
            while (rs.next()) {
                // Nếu cột chứa nhiều hình ảnh, ta chỉ lấy hình đầu tiên
                String[] image = rs.getString(8).split(","); // Tách chuỗi hình ảnh
                type.add(new Products(
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

        return type; // Trả về danh sách các sản phẩm cùng loại
    }

    // Phương thức để lấy tất cả sản phẩm từ cơ sở dữ liệu
    public ArrayList<Products> getByPriceProduct(int min, int max) {
        ArrayList<Products> prod = new ArrayList<>();

        String query = "SELECT P.*,ISNULL(B.brand_name, 'ABCX') AS brand_name\n"
                + "FROM \n"
                + "Products P\n"
                + "JOIN  Brands B ON P.brand_id = B.brand_id \n"
                + "WHERE \n"
                + "price BETWEEN ? AND ?"; // Thêm khoảng trắng

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

    public ArrayList<Products> getProductBrandSame(String brand, int index) {
        ArrayList<Products> type = new ArrayList<>();

        // Câu truy vấn SQL để chọn tất cả các cột từ bảng 'products'
        String query
                = "SELECT \n"
                + "                 P.*, B.Brand_Name\n"
                + "               FROM\n"
                + "                   Products P\n"
                + "               JOIN \n"
                + "                  Brands B ON P.brand_id = B.brand_id\n"
                + "              WHERE \n"
                + "                  B.Brand_Name =?\n"
                + "				  ORDER BY Product_ID \n"
                + "              OFFSET ? ROWS\n"
                + "                FETCH NEXT 6 ROWS ONLY;";

        // Truyền trực tiếp tham số category vào mảng params
        Object[] params = {brand, index};

        // Thực thi truy vấn và lấy kết quả trả về
        try ( ResultSet rs = execSelectQuery(query, params)) {
            // Lặp qua từng hàng trong tập kết quả
            while (rs.next()) {
                // Nếu cột chứa nhiều hình ảnh, ta chỉ lấy hình đầu tiên
                String[] image = rs.getString(8).split(","); // Tách chuỗi hình ảnh
                type.add(new Products(
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

        return type; // Trả về danh sách các sản phẩm cùng loại
    }

    public ArrayList<Products> sortByPriceUp() {
        // Tạo một danh sách trống để lưu các sản phẩm
        ArrayList<Products> prod = new ArrayList<>();

        // Câu truy vấn SQL để chọn tất cả các cột từ bảng 'products'
        String query = "SELECT \n"
                + "    p.*, b.Brand_Name\n"
                + "FROM \n"
                + "    Products p\n"
                + "	JOIN Brands b  ON b.Brand_ID = p.Brand_ID\n"
                + "ORDER BY \n"
                + "    price ASC;";

        // Thực thi truy vấn và lấy kết quả trả về
        try ( ResultSet rs = execSelectQuery(query)) {
            // Lặp qua từng hàng trong tập kết quả
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
        } catch (Exception e) {
            // Xử lý lỗi nếu có
        }
        return prod; // Trả về danh sách các sản phẩm
    }

    public ArrayList<Products> sortByPriceDown() {
        // Tạo một danh sách trống để lưu các sản phẩm
        ArrayList<Products> prod = new ArrayList<>();

        // Câu truy vấn SQL để chọn tất cả các cột từ bảng 'products'
        String query = "SELECT \n"
                + "    p.*, b.Brand_Name\n"
                + "FROM \n"
                + "    Products p\n"
                + "	JOIN Brands b  ON b.Brand_ID = p.Brand_ID\n"
                + "ORDER BY \n"
                + "    price DESC;";

        // Thực thi truy vấn và lấy kết quả trả về
        try ( ResultSet rs = execSelectQuery(query)) {
            // Lặp qua từng hàng trong tập kết quả
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
        } catch (Exception e) {
            // Xử lý lỗi nếu có
        }
        return prod; // Trả về danh sách các sản phẩm
    }

    public ArrayList<Products> sortByNameUp() {
        // Tạo một danh sách trống để lưu các sản phẩm
        ArrayList<Products> prod = new ArrayList<>();

        // Câu truy vấn SQL để chọn tất cả các cột từ bảng 'products'
        String query = "SELECT \n"
                + "    p.*, b.Brand_Name\n"
                + "FROM \n"
                + "    Products p\n"
                + "	JOIN Brands b  ON b.Brand_ID = p.Brand_ID\n"
                + "ORDER BY \n"
                + "    Product_Name ASC;";

        // Thực thi truy vấn và lấy kết quả trả về
        try ( ResultSet rs = execSelectQuery(query)) {
            // Lặp qua từng hàng trong tập kết quả
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
        } catch (Exception e) {
            // Xử lý lỗi nếu có
        }
        return prod; // Trả về danh sách các sản phẩm
    }

    public ArrayList<Products> sortByNameDown() {
        // Tạo một danh sách trống để lưu các sản phẩm
        ArrayList<Products> prod = new ArrayList<>();

        // Câu truy vấn SQL để chọn tất cả các cột từ bảng 'products'
        String query = "SELECT \n"
                + "    p.*, b.Brand_Name\n"
                + "FROM \n"
                + "    Products p\n"
                + "	JOIN Brands b  ON b.Brand_ID = p.Brand_ID\n"
                + "ORDER BY \n"
                + "    Product_Name DESC;";

        // Thực thi truy vấn và lấy kết quả trả về
        try ( ResultSet rs = execSelectQuery(query)) {
            // Lặp qua từng hàng trong tập kết quả
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
        } catch (Exception e) {
            // Xử lý lỗi nếu có
        }
        return prod; // Trả về danh sách các sản phẩm
    }

    public ArrayList<Products> searchProductByName(String search) {
        ArrayList<Products> find = new ArrayList<>();

        // Câu truy vấn SQL để chọn tất cả các cột từ bảng 'products'
        String query = "SELECT \n"
                + "    p.*,\n"
                + "    b.Brand_Name\n"
                + "FROM \n"
                + "    Products p \n"
                + "    JOIN Brands b ON p.Brand_ID = b.Brand_ID\n"
                + "WHERE \n"
                + "    p.product_name LIKE '%' + ? + '%';";
        // Truyền trực tiếp tham số category vào mảng params
        Object[] params = {search};

        // Thực thi truy vấn và lấy kết quả trả về
        try ( ResultSet rs = execSelectQuery(query, params)) {
            // Lặp qua từng hàng trong tập kết quả
            while (rs.next()) {
                // Nếu cột chứa nhiều hình ảnh, ta chỉ lấy hình đầu tiên
                String[] image = rs.getString(5).split(",");

                // Tạo và thêm sản phẩm vào danh sách
                find.add(new Products(
                        rs.getInt(1), // productId
                        rs.getString(2), // productName
                        rs.getString(3), // description
                        rs.getInt(4), // quantity
                        image[0], // hình ảnh đầu tiên
                        rs.getDouble(6), // price
                        rs.getInt(8), // brandName
                        rs.getString(9), // selled
                        rs.getString(10)// type

                ));
            }
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace(); // Hiển thị thông báo lỗi nếu xảy ra
        }

        return find; // Trả về danh sách các sản phẩm cùng loại
    }

    // Hàm lay' tông? sô' trang
    public int getNumberPage() {
        String query = "Select count(*) from Products";
        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 12;
                if (total % 12 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    //Hàm hien thi so san pham trong phân trang
    public List<Products> getPaging(int index, String brand) {
        List<Products> list = new ArrayList<>();
        int pageSize = 12; // Số sản phẩm mỗi trang
        int offset = (index - 1) * pageSize; // Tính OFFSET chuẩn
        if (offset < 0) {
            offset = 0; // Đảm bảo không có giá trị âm
        }
        System.out.println("Offset: " + offset);
        System.out.println("Page Size: " + pageSize);
        System.out.println("Brand: " + brand);

        // Tạo câu truy vấn động (nếu có brand thì lọc, nếu không thì lấy tất cả)
        String query = "SELECT p.*, b.Brand_Name FROM Products p "
                + "JOIN Brands b ON p.Brand_ID = b.Brand_ID ";

        if (brand != null && !brand.isEmpty()) {
            query += "WHERE b.Brand_Name = ? ";
        }

        query += "ORDER BY p.Product_ID ASC, p.Price DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            int paramIndex = 1;
            if (brand != null && !brand.isEmpty()) {
                ps.setString(paramIndex++, brand); // Nếu có brand, set vào query
            }
            ps.setInt(paramIndex++, offset);  // OFFSET chính xác
            ps.setInt(paramIndex, pageSize); // PAGE SIZE chính xác

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] image = rs.getString(5).split(",");
                list.add(new Products(
                        rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), image[0], rs.getDouble(6),
                        rs.getInt(8), rs.getString(9), rs.getString(10)
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
                + "    ISNULL(B.brand_name, 'ABCX') AS brand_name\n"
                + "FROM \n"
                + "    Products P\n"
                + "LEFT JOIN \n"
                + "    Brands B ON P.brand_id = B.brand_id\n"
                + "ORDER BY \n"
                + "    P.Product_ID\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT 6 ROWS ONLY;";
        List<Products> list = new ArrayList<>();
        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, (index - 1) * 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] image = rs.getString(5).split(",");
                list.add(new Products(
                        rs.getInt(1), // Cột ID
                        rs.getString(2), // Cột tên sản phẩm
                        rs.getString(3), // Cột mô tả
                        rs.getInt(4), // Cột số lượng
                        image[0], // Mảng hình ảnh từ cột hình ảnh
                        rs.getDouble(6), // Cột giá
                        rs.getInt(8), // Cột selled
                        rs.getString(9), // Cột tên thương hiệu
                        rs.getString(10) // Cột trạng thái
                ));

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

}
