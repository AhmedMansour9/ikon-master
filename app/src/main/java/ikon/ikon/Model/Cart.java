package ikon.ikon.Model;

/**
 * Created by ic on 9/9/2018.
 */

public class Cart {
    private String count;
    private String id;
    private String Name;
    private String Discroption;
    private String Price;
    private String image;

    public Cart(String count, String id, String name, String discroption, String price, String image) {
        this.count = count;
        this.id = id;
        Name = name;
        Discroption = discroption;
        Price = price;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDiscroption() {
        return Discroption;
    }

    public void setDiscroption(String discroption) {
        Discroption = discroption;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
