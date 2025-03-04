
package model;
import java.text.DecimalFormat;
/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class Products {

    private int productId;
    private String productName;
    private String type;
    private int countInStock;
    private String image;
    private double price;
    private String brand;  // Tên thuộc tính vẫn là "brand"
    private int selled;
    private String description;
    private int quanOrder;
    private String status = "Pending";
 

    public Products() {
    }

     public Products(int productId, String productName, int countInStock, int selled, double price, String image, String description, String type, String brand) {
        this.productId = productId;
        this.productName = productName;
        this.countInStock = countInStock;
        this.selled = selled;
        this.price = price;
        this.image = image;
        this.brand = brand;
        this.type = type; // Gán category
        this.description = description;
    }

    public Products(int productId, String productName, int countInStock, int selled, double price, String image, String description, String type,  String brand,  int quanOrder ) {
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.countInStock = countInStock;
        this.image = image;
        this.price = price;
        this.brand = brand != null ? brand : "";
        this.selled = selled;
        this.description = description;
        this.quanOrder = quanOrder != 0 ? quanOrder : 1;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCountInStock() {
        return countInStock;
    }

    public void setCountInStock(int countInStock) {
        this.countInStock = countInStock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;  // Đổi tên getter thành getBrand
    }

    public void setBrand(String brand) {
        this.brand = brand;  // Đổi tên setter thành setBrand
    }

    public int getSelled() {
        return selled;
    }

    public void setSelled(int selled) {
        this.selled = selled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuanOrder() {
        return quanOrder;
    }

    public void setQuanOrder(int quanOrder) {
        this.quanOrder = quanOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCategory() {
        return category;
    }
}
//    public String formatVietnameseCurrency(double number) {
//    // Chuyển số thành chuỗi, thêm dấu chấm phân cách hàng nghìn, và thêm đơn vị VNĐ
//    DecimalFormat formatter = new DecimalFormat("#,###");
//        // Format số và thêm đơn vị "đ"
//        return formatter.format(number) + " đ";
//}

