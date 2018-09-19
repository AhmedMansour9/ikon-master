package ikon.ikon.Viewes;

import java.util.List;

import ikon.ikon.Model.MaintenanceOrder;

/**
 * Created by ic on 9/17/2018.
 */

public interface ListOrderView {
    void GetListOrderShopping(List<MaintenanceOrder> list);
    void Errorlistorder();
}
