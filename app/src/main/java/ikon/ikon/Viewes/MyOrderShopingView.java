package ikon.ikon.Viewes;

import java.util.List;

import ikon.ikon.Model.Myordershop;

/**
 * Created by ic on 9/20/2018.
 */

public interface MyOrderShopingView {

    void OrderShopping(List<Myordershop> order);
    void Error();
}
