package model;

import java.util.Map;

/**
 * @author tasszz2k
 * @version 1.0
 * @date 08/06/2021 18:35
 */
public class VendingMachine {
    private Integer id;
    private String name;
    private Map<Product, Integer> productQuantityMap; // products map with key (product) - quantity
    private Integer amountOfMoney;

    public VendingMachine() {
    }

    public VendingMachine(Integer id, String name, Map<Product, Integer> productQuantityMap, Integer amountOfMoney) {
        this.id = id;
        this.name = name;
        this.productQuantityMap = productQuantityMap;
        this.amountOfMoney = amountOfMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Product, Integer> getProductQuantityMap() {
        return productQuantityMap;
    }

    public void setProductQuantityMap(Map<Product, Integer> productQuantityMap) {
        this.productQuantityMap = productQuantityMap;
    }

    public Integer getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Integer amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    @Override
    public String toString() {
        return "VendingMachine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productQuantityMap=" + productQuantityMap +
                ", amountOfMoney=" + amountOfMoney +
                '}';
    }
}
