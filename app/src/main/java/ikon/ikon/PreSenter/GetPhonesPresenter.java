package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.IssueResponse;
import ikon.ikon.Model.phonesResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.IssueTybeView;
import ikon.ikon.Viewes.PhonesView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/7/2018.
 */

public class GetPhonesPresenter {

    PhonesView getPhones;

    public GetPhonesPresenter(Context context, PhonesView getPhones)
    {
        this.getPhones=getPhones;

    }

    public void GetPhones(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<phonesResponse> call = apiInterface.GetPHones(queryMap);
        call.enqueue(new Callback<phonesResponse>() {
            @Override
            public void onResponse(Call<phonesResponse> call, Response<phonesResponse> response) {

                if (response.isSuccessful()) {
                    getPhones.getPhones(response.body().getData().getProducts());


                } else {
                    getPhones.Errorphones();
                }
            }


            @Override
            public void onFailure(Call<phonesResponse> call, Throwable t) {
                getPhones.Errorphones();

            }
        });
    }

}
