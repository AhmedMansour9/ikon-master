package ikon.ikon.Retrofit;

import java.util.Map;

import ikon.ikon.Model.AccessoriesResponse;
import ikon.ikon.Model.GetPriceResponse;
import ikon.ikon.Model.IssueResponse;
import ikon.ikon.Model.IssueTubeEnglishResponse;
import ikon.ikon.Model.OrderResponse;
import ikon.ikon.Model.PeoductResponse;
import ikon.ikon.Model.SpartsResponse;
import ikon.ikon.Model.UserLoginResponse;
import ikon.ikon.Model.UserRegisterResponse;
import ikon.ikon.Model.phonesResponse;
import ikon.ikon.Model.RegisterFaceResponse;
import ikon.ikon.PreSenter.OrderShoppinPresenter;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by HP on 04/09/2018.
 */

public interface Apiinterface {


    @POST("signupMobile")
    Call<UserRegisterResponse> register(@QueryMap Map<String,String> queryMab);

    @POST("login")
    Call<UserLoginResponse> Login(@QueryMap Map<String,String> queryMab);

    @POST("signupMobileFacebook")
    Call<RegisterFaceResponse> RegisterFace_Book(@QueryMap Map<String,String> queryMab);

    @POST("signupMobileGoogle")
    Call<UserLoginResponse> RegisterGoogle(@QueryMap Map<String,String> queryMab);

    @POST("productList")
    Call<PeoductResponse> GetProducts(@QueryMap Map<String,String> queryMab);

    @POST("issueTypeList")
    Call<IssueResponse> GetIssueTybe(@QueryMap Map<String,String> queryMab);

    @POST("issueTypeList")
    Call<IssueTubeEnglishResponse> GetIssueEnglish(@QueryMap Map<String,String> queryMab);

    @POST("issuePrice")
    Call<GetPriceResponse> GetPrice(@QueryMap Map<String,String> queryMab);

    @POST("showProductShop")
    Call<phonesResponse> GetPHones(@QueryMap Map<String,String> queryMab);

    @POST("showAccessoriesShop")
    Call<AccessoriesResponse> GetAccessories(@QueryMap Map<String,String> queryMab);

    @POST("showSparePartsShop")
    Call<SpartsResponse> GetSparts(@QueryMap Map<String,String> queryMab);

    @POST("maintenanceOrder")
    Call<OrderResponse> Showorder(@QueryMap Map<String,String> queryMab);

    @POST("shopOrder")
    Call<OrderShoppinPresenter> Ordershop(@QueryMap Map<String,String> queryMab);

}

