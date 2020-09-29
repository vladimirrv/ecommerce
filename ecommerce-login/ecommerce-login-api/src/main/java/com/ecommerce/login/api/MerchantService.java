package com.ecommerce.login.api;

import java.util.Collection;

public interface MerchantService {

    Collection<Merchant> list();

    Merchant get(Long id);

    void add(Merchant merchant);
    
    void update(Merchant merchant);
    
    void remove(Long id);

    void signIn(SignIn signIn);
}
