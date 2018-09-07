package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/6/2018.
 */

public class IssueType {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name_type_ar")
    @Expose
    private String nameTypeAr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameTypeAr() {
        return nameTypeAr;
    }

    public void setNameTypeAr(String nameTypeAr) {
        this.nameTypeAr = nameTypeAr;
    }
    @Override
    public String toString() {
        return nameTypeAr;
    }

}
