package service;

import model.Product;
import model.VendingMachine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tasszz2k
 * @version 1.0
 * @date 09/06/2021 18:36
 */
public class VendingMachineManagement {
    private final MoneyManagement moneyManagement;
    private final ProductManagement productManagement;
    private final VendingMachine vendingMachine;

    public VendingMachineManagement(VendingMachine vendingMachine) {
        moneyManagement = new MoneyManagement();
        productManagement = new ProductManagement();
        this.vendingMachine = vendingMachine;
    }

    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    /**
     * Start Vending Machine
     *
     */
    public void run() {
        // Input money
        System.out.println("====== * ======\n" +
                "Welcome to VendingMachine\n");
        int balance = moneyManagement.inputMoney();

        // Select product
        showAllProduct();
        System.out.println(">> Select Product: ");
        Map<Product, Integer> selectedProducts = selectProducts();
        System.out.println(">> Bill:");
        System.out.println(selectedProducts);
        int total = calculatePayment(selectedProducts);
        System.out.printf(">> Total Payment: %d\n", total);
        if (checkValidBalance(balance, total)) {
            System.out.print(">> Confirm payment(Y/N): ");
            boolean isConfirm = InputValidator.getBoolean();
            if (isConfirm) {
                // Calculate refund
                balance -= total;
                System.out.println(">> Payment success!");
                recalculate(selectedProducts);
            } else {
                System.out.println(">> payment has been canceled!");
            }
        } else {
            System.out.println(">> Payment fail!\n Balance is not enough!");
        }
        System.out.printf(">> Refund: %d\n", balance);
        System.out.print(">> Remaining products");
        showAllProduct();
        System.out.println();
    }

    /**
     * Show all products
     *
     */
    public void showAllProduct() {
        Map<Product, Integer> productQuantityMap = vendingMachine.getProductQuantityMap();
        System.out.println("\n-------------\n" +
                ">> Product list: ");
        StringBuilder productListStringBuilder = new StringBuilder();
        productQuantityMap.forEach((product, value) -> {
            int quantity = value;
            String productString = product.toString();
            productListStringBuilder.append(productString).append(" - quantity: ").append(quantity).append("\n");
        });
        System.out.print(productListStringBuilder);
        System.out.println("-------------");
    }

    /**
     * Select products
     *
     * @return selected products map
     */
    private Map<Product, Integer> selectProducts() {
        Map<Product, Integer> productQuantityMap = vendingMachine.getProductQuantityMap();
        Map<Product, Integer> selectedProducts = new HashMap<>();
        boolean isContinue;
        do {
            System.out.print(">> Select Product ID: ");
            // This must be check in db product is exist
            int productId = selectProduct(productQuantityMap);
            Product product = productManagement.getProductById(productQuantityMap, productId);
            if (product != null) {
                int remainingQuantity = productQuantityMap.get(product) - selectedProducts.getOrDefault(product, 0);
                System.out.printf(">> Select Quantity(max: %d): ", remainingQuantity);
                int quantity = InputValidator.getIntValue(0, remainingQuantity);
                int selectedQuantity = selectedProducts.getOrDefault(product, 0);
                selectedProducts.put(product, selectedQuantity + quantity);
            } else {
                System.out.println("Product does not exist!");
            }
            System.out.print(">> Do you want to continue?(Y/N): ");
            isContinue = InputValidator.getBoolean();
        } while (isContinue);
        return selectedProducts;
    }

    /**
     * Fake data product, must be check in DB
     *
     * @param productQuantityMap
     * @return
     */
    private int selectProduct(Map<Product, Integer> productQuantityMap) {
        return InputValidator.getIntValue(1, productQuantityMap.size());
    }

    /**
     * Calculate total payment
     *
     * @return total money
     */
    private int calculatePayment(Map<Product, Integer> selectedProducts) {
        int total = selectedProducts.entrySet().stream()
                .mapToInt(entry -> {
                    Product product = entry.getKey();
                    int price = product.getPrice();
                    int quantity = entry.getValue();
                    return price * quantity;
                })
                .sum();
        return total;
    }

    private boolean checkValidBalance(int balance, int total) {
        return balance >= total;
    }

    /**
     * Recalculate Vending Machine & update data
     *
     * @param selectedProducts
     */
    private void recalculate(Map<Product, Integer> selectedProducts) {
        Map<Product, Integer> productQuantityMap = vendingMachine.getProductQuantityMap();
        selectedProducts.forEach((product, quantity) -> {
            // update quantity
            int oldQuantity = productQuantityMap.get(product);
            productQuantityMap.put(product, oldQuantity - quantity);
        });
        // update total of money
        int totalPayment = calculatePayment(selectedProducts);
        vendingMachine.setAmountOfMoney(vendingMachine.getAmountOfMoney() + totalPayment);
    }

}
