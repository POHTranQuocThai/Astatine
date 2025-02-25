/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class Order {

    private int orderId;
    private int productId;
    private int userId;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;
    private String email;
    private String phone;
    private int amount;
    private Date orderDate;
    private String status;
    private Double totalPrice;
    private String productName;
    private String customerName;

    public Order() {
    }

    public Order(int orderId, int productId, int userId, int amount) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.amount = amount;
    }

    public Order(int orderId, int productId, int userId, String street, String ward, String district, String city, String country, String email, String phone, int amount, Date orderDate, String status, Double totalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Order(String street, String ward, String district, String city, String country, String phone, String status, Double totalPrice, String email, int userId) {
        this.userId = userId;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.status = status;
        this.totalPrice = totalPrice;
        this.email = email;
    }

    //Get All Order(admin)
    public Order(int orderId, String productName, String customerName, String city, String email, String phone, int amount, Date orderDate, String status, Double totalPrice) {
        this.orderId = orderId;
        this.productName = productName;
        this.customerName = customerName;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    //Edit Order(admin)
    public Order(int orderId, String street, String ward, String district, String city, String country, String status) {
        this.orderId = orderId;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
        this.status = status;
    }

    //Get Order by OrderID(admin)
    public Order(int orderId, String productName, String customerName, String street, String ward, String district, String city, String country, String email, String phone, Date orderDate, String status) {
        this.orderId = orderId;
        this.productName = productName;
        this.customerName = customerName;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.orderDate = orderDate;
        this.status = status;
    }

    //Get Order by Email(profile)
    public Order(int userId, String productName, String city, String email, String phone, int amount, Date orderDate, String status, Double totalPrice) {
        this.userId = userId;
        this.productName = productName;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
