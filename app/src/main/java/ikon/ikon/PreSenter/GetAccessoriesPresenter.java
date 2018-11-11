package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.AccessoriesResponse;
import ikon.ikon.Model.phonesResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.AccessoriesView;
import ikon.ikon.Viewes.PhonesView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/8/2018.
 */

public class GetAccessoriesPresenter {

    AccessoriesView getAccessories;

    public GetAccessoriesPresenter(Context context, AccessoriesView getAccessories)
    {
        this.getAccessories=getAccessories;

    }

    public void GetAccessories(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<AccessoriesResponse> call = apiInterface.GetAccessories(queryMap);
        call.enqueue(new Callback<AccessoriesResponse>() {
            @Override
            public void onResponse(Call<AccessoriesResponse> call, Response<AccessoriesResponse> response) {

                if (response.isSuccessful()) {
                    getAccessories.GetAccessories(response.body().getData().getProducts());
                } else {
                    getAccessories.ErrorAccessories();
                }
            }
            @Override
            public void onFailure(Call<AccessoriesResponse> call, Throwable t) {
                getAccessories.ErrorAccessories();

            }
        });
    }

}
