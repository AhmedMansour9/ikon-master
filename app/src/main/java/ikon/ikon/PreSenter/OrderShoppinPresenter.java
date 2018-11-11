package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.OrderMaintenence;
import ikon.ikon.Model.OrderResponse;
import ikon.ikon.Model.OrderShop;
import ikon.ikon.Model.OrderShopping_Response;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.OrderView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/12/2018.
 */

public class OrderShoppinPresenter {

    OrderView orderss;

    public OrderShoppinPresenter(Context context, OrderView order)
    {
        this.orderss=order;

    }

    public void Order(OrderShop order) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("api_token", "100");
        queryMap.put("address",order.getAddress());
        queryMap.put("latitude", order.getLatitude());
        queryMap.put("longitude",order.getLongetude());
        queryMap.put("user_token", order.getUser_token());
//        if(order.getNote().equals("")) {
            queryMap.put("lang", order.getLang());
//        }else {
            queryMap.put("phone", order.getPhone());
//        }
        queryMap.put("total_price", order.getTotalprice());
        queryMap.put("products", order.getProducts());
        if(order.getPostcode()==null){
            queryMap.put("postcode","1");
        }else {
            queryMap.put("postcode", order.getPostcode());
        }
        if(order.getCity().equals("")){
            queryMap.put("city","");
        }else {
            queryMap.put("city", order.getCity());
        }
        if(order.getCountry().equals("")){
            queryMap.put("country", "");
        }else {
            queryMap.put("country", order.getCountry());
        }


        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<OrderShopping_Response> call = apiInterface.Ordershop(queryMap);
        call.enqueue(new Callback<OrderShopping_Response>() {
            @Override
            public void onResponse(Call<OrderShopping_Response> call, Response<OrderShopping_Response> response) {

                if (response.isSuccessful()) {
                    orderss.OrderSuccess(String.valueOf(response.body().getData().getOrderId()));
                } else {
                    orderss.ErrorOrder();
                }
            }


            @Override
            public void onFailure(Call<OrderShopping_Response> call, Throwable t) {
                orderss.ErrorOrder();

            }
        });
    }

}
