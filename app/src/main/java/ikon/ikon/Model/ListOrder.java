package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ic on 9/16/2018.
 */

public class ListOrder {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("MaintenanceOrder")
    @Expose
    private List<MaintenanceOrder> maintenanceOrder = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MaintenanceOrder> getMaintenanceOrder() {
        return maintenanceOrder;
    }

    public void setMaintenanceOrder(List<MaintenanceOrder> maintenanceOrder) {
        this.maintenanceOrder = maintenanceOrder;
    }
}
