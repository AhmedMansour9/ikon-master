package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ikon.ikon.Model.ShopOrder;

/**
 * Created by ic on 9/17/2018.
 */

public class ListShopping {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("MaintenanceOrder")
    @Expose
    private List<ShopOrder> maintenanceOrder = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ShopOrder> getMaintenanceOrder() {
        return maintenanceOrder;
    }

    public void setMaintenanceOrder(List<ShopOrder> maintenanceOrder) {
        this.maintenanceOrder = maintenanceOrder;
    }
}
