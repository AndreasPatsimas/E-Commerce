package org.patsimas.e_commerce.services;

import org.patsimas.e_commerce.model.Product;

import java.util.List;

public interface CheckoutService {

    void scan(List<String> items);
    double total();

    List<Product> getProducts();
}
