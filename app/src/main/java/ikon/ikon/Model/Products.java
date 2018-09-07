package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/6/2018.
 */

public class Products {
    @SerializedName("products_id")
    @Expose
    private Integer productsId;
    @SerializedName("products_name")
    @Expose
    private String productsName;

    public Integer getProductsId() {
        return productsId;
    }

    public void setProductsId(Integer productsId) {
        this.productsId = productsId;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }
    @Override
    public String toString() {
        return productsName;
    }
}
