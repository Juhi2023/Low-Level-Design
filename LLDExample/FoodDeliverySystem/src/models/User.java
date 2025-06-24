package models;

public class User{
    private int id;
    private String name;
    private String email;
    private String phoneno;

    private double lat;
    private double lon;

    private Cart cart;

    public User(int id, String name, String email, String phoneno, double lat, double lon) {
        this.id = id;
        this.name= name;
        this.email= email;
        this.phoneno = phoneno;
        this.lat = lat;
        this.lon = lon;
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}