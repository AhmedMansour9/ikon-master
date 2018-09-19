package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/16/2018.
 */

public class Accessory {
    @SerializedName("categories_name")
    @Expose
    private String categoriesName;
    @SerializedName("categories_id")
    @Expose
    private String categoriesId;
    @SerializedName("categories_image")
    @Expose
    private String categoriesImage;

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoriesImage() {
        return categoriesImage;
    }

    public void setCategoriesImage(String categoriesImage) {
        this.categoriesImage = categoriesImage;
    }}
