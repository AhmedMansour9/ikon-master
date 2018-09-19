package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/17/2018.
 */

public class ShowOrdersyid {
    @SerializedName("products_id")
    @Expose
    private String productsId;
    @SerializedName("final_price")
    @Expose
    private String finalPrice;
    @SerializedName("products_image")
    @Expose
    private String productsImage;
    @SerializedName("products_name")
    @Expose
    private String productsName;
    @SerializedName("products_description")
    @Expose
    private String productsDescription;

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getProductsImage() {
        return productsImage;
    }

    public void setProductsImage(String productsImage) {
        this.productsImage = productsImage;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public String getProductsDescription() {
        return productsDescription;
    }

    public void setProductsDescription(String productsDescription) {
        this.productsDescription = productsDescription;
    }

}
