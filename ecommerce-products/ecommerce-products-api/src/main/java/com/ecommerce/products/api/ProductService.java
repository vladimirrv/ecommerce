package com.ecommerce.products.api;

import java.util.Collection;

public interface ProductService {

    Collection<Products> list();

    Products get(Long id);

    void add(Products products);
    
    void update(Products products);
    
    void remove(Long id);
}
