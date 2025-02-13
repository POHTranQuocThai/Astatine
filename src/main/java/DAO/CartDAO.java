package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Cart;
import model.Order;
import model.Products;

public class CartDAO extends HashMap<Integer, Cart> {

    public CartDAO() {
    }

    public void addToCart(Cart cart) {
        int productId = cart.getProd().getProductId();

        // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ hàng
        if (this.containsKey(productId)) {
            // Lấy sản phẩm hiện tại trong giỏ hàng
            int oldQuan = ((Cart) this.get(productId)).getQuantity();
            // Tăng số lượng sản phẩm hiện tại lên
            ((Cart) this.get(productId)).setQuantity(oldQuan + cart.getQuantity());
        } else {
            // Nếu sản phẩm chưa tồn tại trong giỏ hàng, đặt số lượng mặc định là 1
            this.put(productId, cart);
        }
    }

    public void decQuantityToCart(Cart cart) {
        int productId = cart.getProd().getProductId();

        // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ hàng
        if (this.containsKey(productId)) {
            // Lấy sản phẩm hiện tại trong giỏ hàng
            int oldQuan = ((Cart) this.get(productId)).getQuantity();
            // Tăng số lượng sản phẩm hiện tại lên
            if (oldQuan > 1) {
                ((Cart) this.get(productId)).setQuantity(oldQuan - cart.getQuantity());
            }
        }
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public boolean removeProductCart(int id) {
        if (this.containsKey(id)) {
            this.remove(id);
            return true;
        } else {
            return false;
        }
    }

    // Lấy danh sách sản phẩm trong giỏ hàng
    public ArrayList<Order> getProductsInCart(int customerId) {
        ArrayList<Order> orderCarts = new ArrayList<>();

        // Duyệt qua tất cả các mục trong HashMap
        for (Map.Entry<Integer, Cart> entry : this.entrySet()) {
            Cart cart = entry.getValue(); // Lấy đối tượng Cart từ entry
            Products product = cart.getProd(); // Lấy sản phẩm từ Cart
            System.out.println("quan"+cart.getQuantity());
            product.setCountInStock(product.getCountInStock()-product.getQuanOrder());
            System.out.println("stock"+product.getCountInStock());
            // Tạo một OrderCart từ thông tin của sản phẩm và số lượng
            Order order = new Order(0, product.getProductId(), customerId, cart.getQuantity());
            // Thêm vào danh sách
            orderCarts.add(order);
        }

        return orderCarts; // Trả về danh sách các OrderCart
    }


    
}
