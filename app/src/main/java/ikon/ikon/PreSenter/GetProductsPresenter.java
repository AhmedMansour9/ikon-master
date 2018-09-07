package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.PeoductResponse;
import ikon.ikon.Model.UserLoginResponse;
import ikon.ikon.Model.UserRegister;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.LoginView;
import ikon.ikon.Viewes.ProductView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/5/2018.
 */

public class GetProductsPresenter {

    ProductView getProducts;

    public GetProductsPresenter(Context context, ProductView ProductView)
    {
        this.getProducts=ProductView;

    }

    public void GetProducts(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<PeoductResponse> call = apiInterface.GetProducts(queryMap);
        call.enqueue(new Callback<PeoductResponse>() {
            @Override
            public void onResponse(Call<PeoductResponse> call, Response<PeoductResponse> response) {

                if (response.isSuccessful()) {
                    getProducts.GetProductsList(response.body().getData().getProducts());

                } else {
                    getProducts.showErrorProductslist("");
                }
            }


            @Override
            public void onFailure(Call<PeoductResponse> call, Throwable t) {
                getProducts.showErrorProductslist("");

            }
        });
    }

}
