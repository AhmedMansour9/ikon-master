package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ic on 9/17/2018.
 */

public class ShowProductsByid {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ProductsOrder")
    @Expose
    private List<ShowOrdersyid> productsOrder = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ShowOrdersyid> getProductsOrder() {
        return productsOrder;
    }

    public void setProductsOrder(List<ShowOrdersyid> productsOrder) {
        this.productsOrder = productsOrder;
    }
}
