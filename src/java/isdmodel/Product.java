/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodel;

/**
 *
 * @author Ella
 */
public class Product {
    
    private int productID;
    private String name;
    private float price;
    private String type;
    private int stockLvl;
    //private String manufacturer;

    public Product(int productID, String name, float price, String type, int stockLvl) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stockLvl = stockLvl;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStockLvl() {
        return stockLvl;
    }

    public void setStockLvl(int quantity) {
        this.stockLvl = quantity;
    }
    
}
