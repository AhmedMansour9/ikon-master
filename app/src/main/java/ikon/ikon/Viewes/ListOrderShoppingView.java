package ikon.ikon.Viewes;

import java.util.List;

import ikon.ikon.Model.MaintenanceOrder;
import ikon.ikon.Model.ShopOrder;

/**
 * Created by ic on 9/17/2018.
 */

public interface ListOrderShoppingView {
    void GetListOrderShopping(List<ShopOrder> list);
    void Errorlistorder();

}
