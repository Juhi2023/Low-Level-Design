package util;

import models.Product;

public class Pair{
    private Product key; 
    private int value;
    public Pair(Product k, int v) { key = k; value = v; }
    public Product getKey()   { return key; }
    public int getValue() { return value; }
}
