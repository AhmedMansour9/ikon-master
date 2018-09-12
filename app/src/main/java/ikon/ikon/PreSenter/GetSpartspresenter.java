package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.SpartsResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.SpartsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/8/2018.
 */

public class GetSpartspresenter {

    SpartsView getAccessories;

    public GetSpartspresenter(Context context, SpartsView getAccessories)
    {
        this.getAccessories=getAccessories;

    }

    public void GetSparts(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<SpartsResponse> call = apiInterface.GetSparts(queryMap);
        call.enqueue(new Callback<SpartsResponse>() {
            @Override
            public void onResponse(Call<SpartsResponse> call, Response<SpartsResponse> response) {

                if (response.isSuccessful()) {
                    getAccessories.GetSparts(response.body().getData().getProducts());


                } else {
                    getAccessories.ErrorSparts();
                }
            }


            @Override
            public void onFailure(Call<SpartsResponse> call, Throwable t) {
                getAccessories.ErrorSparts();

            }
        });
    }

}
