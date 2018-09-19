package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.AccessoriesResponse;
import ikon.ikon.Model.AccessorySubCategoryResoonse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.AccessoriesView;
import ikon.ikon.Viewes.SubCaetgories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/16/2018.
 */

public class AccessorySubCategories {
    SubCaetgories getAccessories;

    public AccessorySubCategories(Context context, SubCaetgories getAccessories)
    {
        this.getAccessories=getAccessories;

    }

    public void GetAccessories(String lang,String id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("cat_id", id);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<AccessorySubCategoryResoonse> call = apiInterface.GetSubCategories(queryMap);
        call.enqueue(new Callback<AccessorySubCategoryResoonse>() {
            @Override
            public void onResponse(Call<AccessorySubCategoryResoonse> call, Response<AccessorySubCategoryResoonse> response) {

                if (response.isSuccessful()) {
                    getAccessories.GetSubCaetgories(response.body().getData().getProducts());


                } else {
                    getAccessories.ErrorSubCategories();
                }
            }


            @Override
            public void onFailure(Call<AccessorySubCategoryResoonse> call, Throwable t) {
                getAccessories.ErrorSubCategories();

            }
        });
    }

}
