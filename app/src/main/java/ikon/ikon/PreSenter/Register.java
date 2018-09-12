package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.UserLoginResponse;
import ikon.ikon.Model.UserRegister;
import ikon.ikon.Model.UserRegisterResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.RegisterView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 04/09/2018.
 */

public class Register {

    RegisterView registerView;

    public Register(Context context, RegisterView registerView)
    {
        this.registerView=registerView;

    }

    public void register(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token", "100");
        queryMap.put("firstName", user.getFirstName());
        queryMap.put("lastName", user.getLastName());
        queryMap.put("password", user.getPassword());
        queryMap.put("email", user.getEmail());
        queryMap.put("phone", user.getPhone());

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<UserRegisterResponse> call = apiInterface.register(queryMap);
        call.enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {

                if (response.isSuccessful()) {
                    registerView.openMain();
                } else {
                    registerView.showError("");
                }
            }


            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                registerView.showError("");

            }
        });
    }
}
