package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.ListOrderResponse;
import ikon.ikon.Model.ShowProductsResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.ListOrderView;
import ikon.ikon.Viewes.ShowProductsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/17/2018.
 */

public class ShowOrdersByid_Presenter {
    ShowProductsView getListorder;

    public ShowOrdersByid_Presenter(Context context, ShowProductsView getAccessories)
    {
        this.getListorder=getAccessories;

    }

    public void GetListOrder(String lang,String id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("order_id", id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<ShowProductsResponse> call = apiInterface.GetListOrderShoppingById(queryMap);
        call.enqueue(new Callback<ShowProductsResponse>() {
            @Override
            public void onResponse(Call<ShowProductsResponse> call, Response<ShowProductsResponse> response) {

                if (response.isSuccessful()) {
                    getListorder.GetListOrderShopping(response.body().getData().getProductsOrder());

                } else {
                    getListorder.Errorlistorder();
                }
            }


            @Override
            public void onFailure(Call<ShowProductsResponse> call, Throwable t) {
                getListorder.Errorlistorder();

            }
        });
    }

}
