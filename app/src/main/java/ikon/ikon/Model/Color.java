package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/13/2018.
 */

public class Color {

    @SerializedName("products_options_values_name")
    @Expose
    private String productsOptionsValuesName;

    public String getProductsOptionsValuesName() {
        return productsOptionsValuesName;
    }

    public void setProductsOptionsValuesName(String productsOptionsValuesName) {
        this.productsOptionsValuesName = productsOptionsValuesName;
    }
    @Override
    public String toString() {
        return productsOptionsValuesName;
    }
}
