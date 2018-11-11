package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.BannserResponsse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.BannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/21/2018.
 */

public class BannerPresenter {
    BannerView getbanner;

    public BannerPresenter(Context context, BannerView ColorView)
    {
        this.getbanner=ColorView;

    }

    public void GetBanner(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<BannserResponsse> call = apiInterface.GetBanner(queryMap);
        call.enqueue(new Callback<BannserResponsse>() {
            @Override
            public void onResponse(Call<BannserResponsse> call, Response<BannserResponsse> response) {

                if (response.isSuccessful()) {
                    getbanner.getBanner(response.body().getData().getBanner());

                } else {
                    getbanner.Errorbaner();
                }
            }


            @Override
            public void onFailure(Call<BannserResponsse> call, Throwable t) {
                getbanner.Errorbaner();

            }
        });
    }

}
