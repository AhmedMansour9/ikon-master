package ikon.ikon.Model;

/**
 * Created by ic on 9/12/2018.
 */

public class Items {
    private String id;
    private String name;
    private String discrption;
    private String price;

    public Items(String id,String name, String discrption, String price) {
        this.id = id;
        this.name = name;
        this.discrption = discrption;
        this.price = price;
    }

    public String getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public String getDiscrption() {
        return discrption;
    }

    public String getPrice() {
        return price;
    }
}
