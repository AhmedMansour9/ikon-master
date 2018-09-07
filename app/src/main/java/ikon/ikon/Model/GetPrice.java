package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/7/2018.
 */

public class GetPrice {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("price")
    @Expose
    private String price;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
