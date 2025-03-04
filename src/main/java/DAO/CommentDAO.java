/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Comment;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class CommentDAO extends db.DBContext {

    public int saveCommentDB(int userId, int productId, String content) {
        String query = "INSERT INTO Comments (Customer_Id, Product_Id, Parent_Comment_Id, Comment_Text, Created_At) "
                + "VALUES (?, ?, ?, ?, ?)";

        Object[] params = {
            userId,
            productId,
            null, // Nếu không có thì truyền null
            content,
            Timestamp.valueOf(LocalDateTime.now()) // Lưu thời gian hiện tại
        };

        try {
            return execQuery(query, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getCommentId(int userId, int productId) throws SQLException {
        String sql = "SELECT Comment_Id FROM Comments WHERE Customer_Id = ? AND Product_Id = ? ORDER BY Created_At DESC LIMIT 1";
        Object[] params = {userId, productId};
        ResultSet rs = execSelectQuery(sql, params);
        if (rs.next()) { // Kiểm tra nếu có dòng kết quả trả về
            return rs.getInt("Comment_Id"); // ✅ Lấy comment_id mới nhất
        }
        return null;
    }

    public int getLikeCount(int commentId) throws SQLException {
        int likeCount = 0;
        String sql = "SELECT COUNT(*) FROM Comment_Reactions WHERE Comment_Id = ?";
        Object[] params = {commentId};
        ResultSet rs = execSelectQuery(sql, params);
        if (rs.next()) { // Kiểm tra nếu có dòng kết quả trả về
            likeCount = rs.getInt(1);
        }
        return likeCount;
    }

    public boolean checkUserLiked(int userId, int commentId) throws SQLException {
        String sql = "SELECT * FROM Comment_Reactions WHERE Customer_Id = ? AND Comment_Id = ?";
        Object[] params = {userId, commentId};
        ResultSet rs = execSelectQuery(sql, params);
        return rs.next();
    }

    public void addLike(int userId, int commentId) throws SQLException {
        String sql = "INSERT INTO Comment_Reactions (Customer_Id, Comment_Id) VALUES (?, ?)";
        Object[] params = {userId, commentId};
        execQuery(sql, params); // Sửa lại thành execQuery()
    }

    public void removeLike(int userId, int commentId) throws SQLException {
        String sql = "DELETE FROM Comment_Reactions WHERE Customer_Id = ? AND Comment_Id = ?";
        Object[] params = {userId, commentId};
        execQuery(sql, params); // Sửa lại thành execQuery()
    }

    public ArrayList<Comment> getCommentByProductId(int productId) {
        ArrayList<Comment> comment = new ArrayList<>();
        DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd"); // Định dạng trong DB

        String query = "SELECT Comment_Id, Customer_Id, Product_Id, Comment_Text, Created_At, Parent_Comment_Id FROM Comments WHERE Product_Id = ? ORDER BY Created_At ASC";
        Object[] params = {productId};

        try ( ResultSet rs = execSelectQuery(query, params)) {
            while (rs.next()) {
                Timestamp createdAtTimestamp = rs.getTimestamp("Created_At");
                LocalDateTime createdAt = (createdAtTimestamp != null) ? createdAtTimestamp.toLocalDateTime() : null;

                comment.add(new Comment(
                        rs.getInt("Comment_Id"),
                        rs.getInt("Customer_Id"),
                        rs.getInt("Product_Id"),
                        rs.getInt("Parent_Comment_Id"),
                        rs.getString("Comment_Text"),
                        createdAt // Lưu vào đối tượng Comment dạng LocalDateTime
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }

}
