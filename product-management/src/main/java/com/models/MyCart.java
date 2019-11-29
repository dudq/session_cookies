package com.models;

public class MyCart {

    private Long id;
    private Integer quantityBuy;

    public MyCart() {
    }

    public MyCart(Long id, Integer quantityBuy) {
        this.id = id;
        this.quantityBuy = quantityBuy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantity(Integer quantityBuy) {
        this.quantityBuy = quantityBuy;
    }

    public Integer incrementQuantityBuy() {
        return quantityBuy++;
    }
}
