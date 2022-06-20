package org.patsimas.e_commerce.services;

import java.util.List;

public interface CheckoutService {

    void scan(List<String> items);
    double total();
}
