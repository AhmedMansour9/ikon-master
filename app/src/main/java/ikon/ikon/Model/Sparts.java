package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ic on 9/8/2018.
 */

public class Sparts {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("products")
    @Expose
    private List<Spart> products = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Spart> getProducts() {
        return products;
    }

    public void setProducts(List<Spart> products) {
        this.products = products;
    }
}
