package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.ListOrderResponse;
import ikon.ikon.Model.ListOrderShoppingResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.ListOrderShoppingView;
import ikon.ikon.Viewes.ListOrderView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/17/2018.
 */

public class ListOrderShopping_Presenter {

    ListOrderShoppingView getListorder;

    public ListOrderShopping_Presenter(Context context, ListOrderShoppingView getAccessories)
    {
        this.getListorder=getAccessories;

    }

    public void GetListOrder(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<ListOrderShoppingResponse> call = apiInterface.GetListOrderShopping(queryMap);
        call.enqueue(new Callback<ListOrderShoppingResponse>() {
            @Override
            public void onResponse(Call<ListOrderShoppingResponse> call, Response<ListOrderShoppingResponse> response) {

                if (response.isSuccessful()) {
                    getListorder.GetListOrderShopping(response.body().getData().getMaintenanceOrder());


                } else {
                    getListorder.Errorlistorder();
                }
            }


            @Override
            public void onFailure(Call<ListOrderShoppingResponse> call, Throwable t) {
                getListorder.Errorlistorder();

            }
        });
    }

}
