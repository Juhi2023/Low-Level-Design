
package product;

import models.Product;

public class GroceryProduct extends Product{
    String brand;
    
    public GroceryProduct(int sku, String name, double price){
        super(sku, name, price);
    }

    public void setBrand(String val){
        brand = val;
    }

    public String getBrand(){
        return brand;
    }
}