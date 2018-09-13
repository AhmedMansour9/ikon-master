package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.OrderMaintenence;
import ikon.ikon.Model.OrderResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.OrderView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/9/2018.
 */

public class OrderPresenter {

    OrderView orderss;

    public OrderPresenter(Context context, OrderView order)
    {
        this.orderss=order;

    }

    public void Order(OrderMaintenence order) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("api_token", "100");
        queryMap.put("sparePart",order.getProductid());
        queryMap.put("type",order.getTybe());
        queryMap.put("color", order.getColor());
        if(order.getNote().equals("")) {
            queryMap.put("note", "null");
        }else {
            queryMap.put("note", order.getNote());
        }
        queryMap.put("address", order.getAddress());
        queryMap.put("latitude", order.getLatitude());
        queryMap.put("longitude", order.getLongetude());
        queryMap.put("user_token", order.getUser_token());
        queryMap.put("issue", order.getIssueid());
        queryMap.put("price", order.getPrice());

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<OrderResponse> call = apiInterface.Showorder(queryMap);
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {

                if (response.isSuccessful()) {
                    orderss.OrderSuccess();
                } else {
                    orderss.ErrorOrder();
                }
            }


            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                orderss.ErrorOrder();

            }
        });
    }

}
