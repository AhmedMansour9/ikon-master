package ikon.ikon.Viewes;

import java.util.List;

import ikon.ikon.Model.Accessory;
import ikon.ikon.Model.AccessorysubCategory;

/**
 * Created by ic on 9/8/2018.et
 */

public interface AccessoriesView {

    void GetAccessories(List<Accessory> list);
    void ErrorAccessories();
}
