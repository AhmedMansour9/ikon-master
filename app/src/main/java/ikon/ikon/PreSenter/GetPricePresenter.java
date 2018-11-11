package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.GetPriceResponse;
import ikon.ikon.Model.PeoductResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.GetPriceView;
import ikon.ikon.Viewes.ProductView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/7/2018.
 */

public class GetPricePresenter {

    GetPriceView getPrice;

    public GetPricePresenter(Context context, GetPriceView getPrice)
    {
        this.getPrice=getPrice;

    }

    public void GetProducts(String phoneid,String Productid,String Issueid,String tybe) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("sparePart", Productid);
        queryMap.put("api_token", "100");
        queryMap.put("product_id", phoneid);
        queryMap.put("type", tybe);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<GetPriceResponse> call = apiInterface.GetPrice(queryMap);
        call.enqueue(new Callback<GetPriceResponse>() {
            @Override
            public void onResponse(Call<GetPriceResponse> call, Response<GetPriceResponse> response) {

                if (response.isSuccessful()) {
                    getPrice.Price(response.body().getData().getPrice());

                } else {
                    getPrice.ErrorPrice();
                }
            }


            @Override
            public void onFailure(Call<GetPriceResponse> call, Throwable t) {
                getPrice.ErrorPrice();

            }
        });
    }

}
