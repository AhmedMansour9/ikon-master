package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.BannerResponse;
import ikon.ikon.Model.ColorResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.BannerView;
import ikon.ikon.Viewes.ColorView;
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


        Call<BannerResponse> call = apiInterface.GetBanner(queryMap);
        call.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {

                if (response.isSuccessful()) {
                    getbanner.getBanner(response.body().getBanner());

                } else {
                    getbanner.Errorbaner();
                }
            }


            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {
                getbanner.Errorbaner();

            }
        });
    }

}
