package factory;

import product.*;
import models.Product;
import enums.ProductType;

public class ProductFactory{

    public static Product createProduct(ProductType t, int sku, String name, double price){
        switch(t){
            case ProductType.GROCERY : return new GroceryProduct(sku, name, price);
            case ProductType.CLOTHING : return new ClothingProduct(sku, name, price);
            default: return new Product(sku, name, price);
        }
    }
}