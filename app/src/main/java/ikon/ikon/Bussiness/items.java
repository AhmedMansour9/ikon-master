package ikon.ikon.Bussiness;

import java.util.ArrayList;
import java.util.List;

import ikon.ikon.Model.Items;

/**
 * Created by ic on 9/12/2018.
 */

public class items {
    static List<Items> item=new ArrayList<>();

    public void Listitem(Items list){

        item.add(list);
    }
    public List<Items> getlist(){

        return item;
    }
}
