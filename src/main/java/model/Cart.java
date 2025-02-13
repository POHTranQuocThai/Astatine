/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class Cart implements Serializable{
    private Products prod;
    private int quantity;

    public Cart() {
    }

    public Cart(Products prod, int quantity) {
        this.prod = prod;
        this.quantity = quantity;
    }

    public Cart(Products prod) {
        this.prod = prod;
        this.quantity = 1;
    }
    

    public Products getProd() {
        return prod;
    }

    public void setProd(Products prod) {
        this.prod = prod;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
