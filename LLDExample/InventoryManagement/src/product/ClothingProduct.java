package product;

import models.Product;

public class ClothingProduct extends Product{
    boolean isRefrigerated;
    
    public ClothingProduct(int sku, String name, double price){
        super(sku, name, price);
    }

    public void setIsRefigrated(boolean val){
        isRefrigerated = val;
    }

    public boolean getIsRefigrated(){
        return isRefrigerated;
    }
}