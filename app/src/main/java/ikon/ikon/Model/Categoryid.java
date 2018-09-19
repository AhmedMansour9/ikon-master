package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ic on 9/16/2018.
 */

public class Categoryid {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("products")
    @Expose
    private List<AccessorysubCategory> products = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AccessorysubCategory> getProducts() {
        return products;
    }

    public void setProducts(List<AccessorysubCategory> products) {
        this.products = products;
    }
}
