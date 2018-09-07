package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Activites.RegisterResponse;
import ikon.ikon.Model.UserLoginResponse;
import ikon.ikon.Model.UserRegister;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.LoginView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 04/09/2018.
 */

public class LoginPresenter {

    LoginView loginvieew;

    public LoginPresenter(Context context, LoginView Loginview)
    {
        this.loginvieew=Loginview;

    }

    public void Login(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("email", user.getEmail());
        queryMap.put("password", user.getPassword());
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<UserLoginResponse> call = apiInterface.Login(queryMap);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {

                if (response.isSuccessful()) {
                        loginvieew.openMain(response.body().getData().getUserToken());

                } else {
                    loginvieew.showError("");
                }
            }


            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                loginvieew.showError("");

            }
        });
    }
}
