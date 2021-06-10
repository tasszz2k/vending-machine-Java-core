package service;

import model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tasszz2k
 * @version 1.0
 * @date 09/06/2021 19:14
 */
public class ProductManagement {

    public Map<Product, Integer> fakeDataProductMap() {
        final Map<Product, Integer> productQuantityMap = new HashMap<>();

        productQuantityMap.put(new Product(1, "Coke", 10000), 15);
        productQuantityMap.put(new Product(2, "Pepsi", 10000), 15);
        productQuantityMap.put(new Product(3, "Soda", 20000), 15);
        return productQuantityMap;
    }

    /**
     * Find product by Id
     *
     * @param productQuantityMap Map<Product, quantity>
     * @param id id product
     * @return product with id input
     */
    public Product getProductById(Map<Product, Integer> productQuantityMap, int id) {
        Product product = null;
        product = productQuantityMap.keySet().stream()
                .filter(product1 -> product1.getId().equals(id))
                .findFirst().orElse(null);
        return product;
    }

}
