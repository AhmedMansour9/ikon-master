package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ic on 9/5/2018.
 */

public class Product {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("products")
    @Expose
    private List<Products> products = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }


}
