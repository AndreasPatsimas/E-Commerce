package org.patsimas.e_commerce.repositories;

import org.patsimas.e_commerce.model.Product;

import java.util.List;

public class ProductRepository {

    private static ProductRepository instance = new ProductRepository();

    private ProductRepository() {}

    public static ProductRepository getInstance() {
        return instance;
    }

    public List<Product> fetchProducts() {

        return List.of(
                new Product("001", "Water Bottle", 24.95),
                new Product("002", "Hoodie", 65.00),
                new Product("003", "Sticker Set", 3.99)
        );
    }

    public Product fetchProductById(String id) {

        List<Product> products = fetchProducts();

        return products.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }
}
