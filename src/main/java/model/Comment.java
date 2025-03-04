package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {

    private int id;
    private int customerId;
    private int productId;
    private int parentId;
    private String commentText;
    private LocalDateTime createdAt;

    // ✅ Constructor cần có đầy đủ tham số
    public Comment(int id, int customerId, int productId, int parentId, String commentText, LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.parentId = parentId;
        this.commentText = commentText;
        this.createdAt = createdAt;
    }
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Định dạng ngày giờ khi cần hiển thị
    public String getFormattedCreatedAt() {
        return createdAt != null ? createdAt.format(FORMATTER) : "N/A";
    }
}
