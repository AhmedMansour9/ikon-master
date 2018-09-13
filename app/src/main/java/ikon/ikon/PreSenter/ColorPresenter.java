package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.AccessoriesResponse;
import ikon.ikon.Model.ColorResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.AccessoriesView;
import ikon.ikon.Viewes.ColorView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/13/2018.
 */

public class ColorPresenter {
    ColorView getColor;

    public ColorPresenter(Context context, ColorView ColorView)
    {
        this.getColor=ColorView;

    }

    public void GetColor(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<ColorResponse> call = apiInterface.GetColors(queryMap);
        call.enqueue(new Callback<ColorResponse>() {
            @Override
            public void onResponse(Call<ColorResponse> call, Response<ColorResponse> response) {

                if (response.isSuccessful()) {
                    getColor.getColor(response.body().getData().getColor());

                } else {
                    getColor.ErrorColor();
                }
            }


            @Override
            public void onFailure(Call<ColorResponse> call, Throwable t) {
                getColor.ErrorColor();

            }
        });
    }

}
