/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class Voucher {
     private int voucherId;
     private String name;
     private int discount;
     private String expiry;
     private int orderId;

    public Voucher() {
    }

    public Voucher(int voucherId, String name, int discount, String expiry, int orderId) {
        this.voucherId = voucherId;
        this.name = name;
        this.discount = discount;
        this.expiry = expiry;
        this.orderId = orderId;
    }

    public Voucher(int voucherId, String name, int discount, String expiry) {
        this.voucherId = voucherId;
        this.name = name;
        this.discount = discount;
        this.expiry = expiry;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
     
    
}
