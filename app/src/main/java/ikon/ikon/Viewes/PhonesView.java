package ikon.ikon.Viewes;

import java.util.List;

import ikon.ikon.Model.Phone;
import ikon.ikon.Model.Phones;

/**
 * Created by ic on 9/7/2018.
 */

public interface PhonesView {

    void getPhones(List<Phones> phone);
    void Errorphones();
}
