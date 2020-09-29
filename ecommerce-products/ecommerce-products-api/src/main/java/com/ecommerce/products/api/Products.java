package com.ecommerce.products.api;

import com.commerce.discount.api.api.Discount;

import java.util.List;

/**
 * A regular POJO.
 */
public class Products {

    private Long id;
    private String name;
    private String description;
    private Long inventory;
    private Long unitPrice;
    private String delivery;
    private String category;
    private String paymentOptions;
    private List<Discount> discountsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(String paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public List<Discount> getDiscountsList() {
        return discountsList;
    }

    public void setDiscountsList(List<Discount> discountsList) {
        this.discountsList = discountsList;
    }
}
