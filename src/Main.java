import model.Product;
import model.VendingMachine;
import service.ProductManagement;
import service.VendingMachineManagement;

import java.util.Map;

/**
 * @author tasszz2k
 * @version 1.0
 * @date 08/06/2021 18:32
 */
public class Main {

    public static void main(String[] args) {
        VendingMachineManagement vmm = new VendingMachineManagement(prepareData());
        while(true){
            vmm.run();
        }
    }

    /**
     * Fake pre Data
     *
     * @return VendingMachine
     */
    public static VendingMachine prepareData() {
        ProductManagement productManagement = new ProductManagement();
        Map<Product, Integer> productQuantityMap = productManagement.fakeDataProductMap();
        VendingMachine vendingMachine = new VendingMachine(1, "Vending Machine 1", productQuantityMap, 2000000);
        return vendingMachine;
    }
}
