package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/8/2018.
 */

public class IssueTybeEnglish {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name_type_en")
    @Expose
    private String nameTypeEn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameTypeEn() {
        return nameTypeEn;
    }

    public void setNameTypeEn(String nameTypeEn) {
        this.nameTypeEn = nameTypeEn;
    }

    @Override
    public String toString() {
        return nameTypeEn;
    }
}
