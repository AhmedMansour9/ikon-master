package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.IssueResponse;
import ikon.ikon.Model.IssueType;
import ikon.ikon.Model.PeoductResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.IssueTybeView;
import ikon.ikon.Viewes.ProductView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/6/2018.
 */

public class GetIssuePresenter {

    IssueTybeView getIssuetyb;

    public GetIssuePresenter(Context context, IssueTybeView Issueeview)
    {
        this.getIssuetyb=Issueeview;

    }

    public void GetIssuetybe(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<IssueResponse> call = apiInterface.GetIssueTybe(queryMap);
        call.enqueue(new Callback<IssueResponse>() {
            @Override
            public void onResponse(Call<IssueResponse> call, Response<IssueResponse> response) {

                if (response.isSuccessful()) {
                    getIssuetyb.GetIssuetybe(response.body().getData().getIssueType());

                } else {
                    getIssuetyb.showErrorIssuetybe("");
                }
            }


            @Override
            public void onFailure(Call<IssueResponse> call, Throwable t) {
                getIssuetyb.showErrorIssuetybe("");

            }
        });
    }

}
