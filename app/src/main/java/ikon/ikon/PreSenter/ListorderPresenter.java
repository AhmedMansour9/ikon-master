package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.ListOrderResponse;
import ikon.ikon.Model.SpartsResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.ListOrderView;
import ikon.ikon.Viewes.SpartsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/17/2018.
 */

public class ListorderPresenter {


    ListOrderView getListorder;

    public ListorderPresenter(Context context, ListOrderView getAccessories)
    {
        this.getListorder=getAccessories;

    }

    public void GetListOrder(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<ListOrderResponse> call = apiInterface.GetListOrder(queryMap);
        call.enqueue(new Callback<ListOrderResponse>() {
            @Override
            public void onResponse(Call<ListOrderResponse> call, Response<ListOrderResponse> response) {

                if (response.isSuccessful()) {
                    getListorder.GetListOrderShopping(response.body().getData().getMaintenanceOrder());


                } else {
                    getListorder.Errorlistorder();
                }
            }


            @Override
            public void onFailure(Call<ListOrderResponse> call, Throwable t) {
                getListorder.Errorlistorder();

            }
        });
    }

    public void GetListOrder(String lang,String usertoken) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", usertoken);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<ListOrderResponse> call = apiInterface.GetListOrder(queryMap);
        call.enqueue(new Callback<ListOrderResponse>() {
            @Override
            public void onResponse(Call<ListOrderResponse> call, Response<ListOrderResponse> response) {

                if (response.isSuccessful()) {
                    getListorder.GetListOrderShopping(response.body().getData().getMaintenanceOrder());


                } else {
                    getListorder.Errorlistorder();
                }
            }


            @Override
            public void onFailure(Call<ListOrderResponse> call, Throwable t) {
                getListorder.Errorlistorder();

            }
        });
    }

}
