package ikon.ikon.Viewes;

import java.util.List;

import ikon.ikon.Model.Product;
import ikon.ikon.Model.Products;

/**
 * Created by ic on 9/5/2018.
 */

public interface ProductView {
    void GetProductsList(List<Products> a);
    void showErrorProductslist(String error);
}
