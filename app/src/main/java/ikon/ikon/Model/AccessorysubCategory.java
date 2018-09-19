package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/8/2018.
 */

public class AccessorysubCategory {

    @SerializedName("products_id")
    @Expose
    private String productsId;
    @SerializedName("products_name")
    @Expose
    private String productsName;
    @SerializedName("products_description")
    @Expose
    private String productsDescription;
    @SerializedName("products_model")
    @Expose
    private String productsModel;
    @SerializedName("products_image")
    @Expose
    private String productsImage;
    @SerializedName("products_price")
    @Expose
    private String productsPrice;

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
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

    public String getProductsModel() {
        return productsModel;
    }

    public void setProductsModel(String productsModel) {
        this.productsModel = productsModel;
    }

    public String getProductsImage() {
        return productsImage;
    }

    public void setProductsImage(String productsImage) {
        this.productsImage = productsImage;
    }

    public String getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(String productsPrice) {
        this.productsPrice = productsPrice;
    }
}
