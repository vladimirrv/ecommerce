package com.commerce.discount.api.api;

import java.util.Collection;

/**
 * Simple interface
 */
public interface DiscountService {

    Collection<Discount> list();

    Discount get(Long id);

    void add(Discount discount);
    
    void update(Discount discount);
    
    void remove(Long id);
}
