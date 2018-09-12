package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.RegisterFaceResponse;
import ikon.ikon.Model.UserRegister;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.RegisterFaceView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 04/09/2018.
 */

public class RegisterFace_Presenter {

    RegisterFaceView loginFacevieew;

    public RegisterFace_Presenter(Context context, RegisterFaceView Loginview)
    {
        this.loginFacevieew=Loginview;

    }

    public void RegisterFace(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("name", user.getFirstName());
        queryMap.put("email", user.getEmail());
        queryMap.put("fb_id", user.getId());
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<RegisterFaceResponse> call = apiInterface.RegisterFace_Book(queryMap);
        call.enqueue(new Callback<RegisterFaceResponse>() {
            @Override
            public void onResponse(Call<RegisterFaceResponse> call, Response<RegisterFaceResponse> response) {

                if (response.isSuccessful()) {

                        loginFacevieew.openMainFace(response.body().getData().getUserToken());
                    }else {
                        loginFacevieew.showErrorFace("");

                    }


            }


            @Override
            public void onFailure(Call<RegisterFaceResponse> call, Throwable t) {
                loginFacevieew.showErrorFace("");

            }
        });
    }
}
