package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.ListOrderShoppingResponse;
import ikon.ikon.Model.MyorderShoping;
import ikon.ikon.Model.MyorderShopingResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.ListOrderShoppingView;
import ikon.ikon.Viewes.MyOrderShopingView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/20/2018.
 */

public class MyOrderShoppingPresenter {
    MyOrderShopingView getListorder;

    public MyOrderShoppingPresenter(Context context, MyOrderShopingView getAccessories)
    {
        this.getListorder=getAccessories;

    }

    public void GetListOrderShoping(String lang,String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("user_token", user);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<MyorderShopingResponse> call = apiInterface.GetListMyOrderShoping(queryMap);
        call.enqueue(new Callback<MyorderShopingResponse>() {
            @Override
            public void onResponse(Call<MyorderShopingResponse> call, Response<MyorderShopingResponse> response) {

                if (response.isSuccessful()) {
                    getListorder.OrderShopping(response.body().getData().getMaintenanceOrder());


                } else {
                    getListorder.Error();
                }
            }


            @Override
            public void onFailure(Call<MyorderShopingResponse> call, Throwable t) {
                getListorder.Error();

            }
        });
    }

}
