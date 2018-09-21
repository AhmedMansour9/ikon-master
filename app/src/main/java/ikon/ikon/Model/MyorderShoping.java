package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ic on 9/20/2018.
 */

public class MyorderShoping {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ShopOrder")
    @Expose
    private List<Myordershop> maintenanceOrder = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Myordershop> getMaintenanceOrder() {
        return maintenanceOrder;
    }

    public void setMaintenanceOrder(List<Myordershop> maintenanceOrder) {
        this.maintenanceOrder = maintenanceOrder;
    }
}
