package ikon.ikon.Bussiness;

import java.util.ArrayList;
import java.util.List;

import ikon.ikon.Model.Cart;

/**
 * Created by ic on 9/9/2018.
 */

public class ListItemCart {

   public static List<Cart> item=new ArrayList<>();

    public void Listitem(Cart list){

        item.add(list);
    }
    public List<Cart> getlist(){

        return item;
    }
}
